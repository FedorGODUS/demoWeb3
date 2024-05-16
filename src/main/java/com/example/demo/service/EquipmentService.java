package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Equipment;
import com.example.demo.model.enums.Status;
import com.example.demo.model.enums.UniqEquipment;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.EquipmentRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EquipmentService {
    private final EquipmentRepo equipmentRepo;
    private final AccountRepo accountRepo;

    public EquipmentService(EquipmentRepo equipmentRepo, AccountRepo accountRepo) {
        this.equipmentRepo = equipmentRepo;
        this.accountRepo = accountRepo;
    }

    public Equipment mint(String accountId, UniqEquipment uniqEquipment){
        Optional<Account> account = accountRepo.findById(accountId);
        double balance = 0;
        if (account.isPresent()) {
            switch (uniqEquipment.getType()) {
                case LAPTOP -> balance = account.get().getBalanceLaptop();
                case ASIK -> balance = account.get().getBalanceAsik();
                case VIDEOCARD -> balance = account.get().getBalanceVideoCard();
            }
        }
        if (balance>=uniqEquipment.getPrice()) {
            Equipment equipment = new Equipment();
            equipment.setAccountId(accountId);
            equipment.setUniqEquipment(uniqEquipment);
            equipment.setStatus(Status.NOTUSED);
            return equipmentRepo.save(equipment);
        }
        return null;
    }

    public List<Equipment> findAllUsedEquipmentByAccountId(String accountId){
        List<Equipment> equipmentList = equipmentRepo.findAllByAccountId(accountId);
        equipmentList.removeIf(equipment -> Status.NOTUSED.equals(equipment.getStatus()));
        return equipmentList;
    }

    public Equipment makeUsed(String accountId, long equipmentId){
        Equipment equipment = equipmentRepo.findById(equipmentId).get();
        if (Objects.equals(equipment.getAccountId(), accountId) && equipment.getStatus() == Status.NOTUSED){
            equipment.setStatus(Status.USED);
            equipment.setLastCollect(Date.from(Instant.ofEpochSecond(System.currentTimeMillis())));
            equipmentRepo.save(equipment);
        }
        return equipment;
    }

    public List<Equipment> getAllEquipment(){
        return equipmentRepo.findAll();
    }
}
