package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Equipment;
import com.example.demo.model.enums.Type;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.EquipmentRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final EquipmentRepo equipmentRepo;
    private final EquipmentService equipmentService;

    public AccountService(AccountRepo accountRepo, EquipmentRepo equipmentRepo, EquipmentService equipmentService) {
        this.accountRepo = accountRepo;
        this.equipmentRepo = equipmentRepo;
        this.equipmentService = equipmentService;
    }

    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    public void deposit(String cryptoId, int amount){
        Account account = accountRepo.getAccountByCryptoId(cryptoId);
        account.setBalance(amount);
        accountRepo.save(account);

    }

    public Account getAccount(String cryptoId){
        return accountRepo.getAccountByCryptoId(cryptoId);
    }

    public Account collect(String cryptoId){
        double laptopAmount = 0;
        double asikAmount = 0;
        double videoCardAmount = 0;
        Type type;
        double amount;
        long time;
        Account account = accountRepo.getAccountByCryptoId(cryptoId);
        List<Equipment> equipmentList = equipmentService.findAllUsedEquipmentByAccountId(cryptoId);
        for (Equipment equipment : equipmentList) {
            type = equipment.getUniqEquipment().getType();
            amount = equipment.getUniqEquipment().getAmount();
            time = equipment.getLastCollect().getTime() - System.currentTimeMillis();
            switch (type) {
                case LAPTOP -> laptopAmount = amount * time / (60 * 1000) / 60;
                case ASIK -> asikAmount = amount * time / (60 * 1000) / 60;
                case VIDEOCARD -> videoCardAmount = amount * time / (60 * 1000) / 60;
            }
            equipment.setLastCollect(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())));
            equipmentRepo.save(equipment);
        }
        laptopAmount = laptopAmount +  account.getBalanceLaptop();
        asikAmount = asikAmount + account.getBalanceAsik();
        videoCardAmount = videoCardAmount + account.getBalanceVideoCard();
        account.setBalanceLaptop(laptopAmount);
        account.setBalanceAsik(asikAmount);
        account.setBalanceVideoCard(videoCardAmount);
        accountRepo.save(account);
        return account;
    }
}
