package com.example.demo.model.enums;

import lombok.Getter;

@Getter
public enum UniqEquipment {
    BRONZE_LAPTOP("Бронзовый лептоп", Type.LAPTOP, Rarity.BRONZE, 122,99),
    SILVER_LAPTOP("Серебрянный лептоп", Type.LAPTOP, Rarity.SILVER, 466,999);

    private String name;
    private Type type;
    private Rarity rarity;
    private double amount;
    private int price;
    UniqEquipment(String name, Type type, Rarity rarity, double amount, int price) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.amount = amount;
        this.price = price;
    }
}
