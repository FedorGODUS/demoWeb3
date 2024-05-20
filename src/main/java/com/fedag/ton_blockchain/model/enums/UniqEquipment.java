package com.fedag.ton_blockchain.model.enums;

import lombok.Getter;

@Getter
public enum UniqEquipment {
    BRONZE_LAPTOP("Бронзовый лептоп", Type.LAPTOP, Rarity.BRONZE, 122,99),
    SILVER_LAPTOP("Серебрянный лептоп", Type.LAPTOP, Rarity.SILVER, 466,999);

    private final String name;
    private final Type type;
    private final Rarity rarity;
    private final double amount;
    private final int price;
    UniqEquipment(String name, Type type, Rarity rarity, double amount, int price) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.amount = amount;
        this.price = price;
    }
}
