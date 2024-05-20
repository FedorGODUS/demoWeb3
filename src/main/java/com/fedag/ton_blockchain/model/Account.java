package com.fedag.ton_blockchain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Account {
    @Id
    private String cryptoId;
    private double balance;
    private double balanceLaptop;
    private double balanceVideoCard;
    private double balanceAsik;
}
