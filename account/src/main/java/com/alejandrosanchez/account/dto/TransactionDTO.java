package com.alejandrosanchez.account.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
    private LocalDateTime date;
    private String type;
    private double amount;
    private double balanceAfter;

    public TransactionDTO(LocalDateTime date, String type, double amount, double balanceAfter) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }
}
