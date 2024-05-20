package com.fedag.ton_blockchain.controller;

import com.fedag.ton_blockchain.model.Account;
import com.fedag.ton_blockchain.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    private ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/{cryptoID}")
    private ResponseEntity<Double> getBalance(@PathVariable("cryptoID") String cryptoID) {
        return new ResponseEntity<>(accountService.getAccount(cryptoID).getBalance(), HttpStatus.OK);
    }

    @PostMapping("/deposit")
    private ResponseEntity<Account> deposit(@RequestParam String cryptoId, @RequestParam int amount) {
        accountService.deposit(cryptoId, amount);
        return new ResponseEntity<>(accountService.getAccount(cryptoId), HttpStatus.OK);
    }

    @PostMapping("/collect")
    private ResponseEntity<Account> collect(@RequestParam String cryptoId) {
        return new ResponseEntity<>(accountService.collect(cryptoId), HttpStatus.OK);
    }
}
