package com.example.javalab2.domain;

public class Wallet extends Entity{
    private int money;

    public Wallet() {
    }

    public Wallet(Integer id, int money) {
        super(id);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
