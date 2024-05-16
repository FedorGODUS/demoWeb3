package com.example.demo.repo;

import com.example.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

    Account getAccountByCryptoId(String cryptoId);
    //int getBalance(String cryptoId);
    //void deposit(String cryptoId, double amount);
}
