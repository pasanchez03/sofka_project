package com.alejandrosanchez.account.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountDetailsDTO {
    private String accountNumber;
    private String accountType;
    private double balance;
    private List<TransactionDTO> transactions;

    public AccountDetailsDTO(String accountNumber, String accountType, double balance, List<TransactionDTO> transactions) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.transactions = transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
