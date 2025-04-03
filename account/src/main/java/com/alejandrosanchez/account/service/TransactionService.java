package com.alejandrosanchez.account.service;

import com.alejandrosanchez.account.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAll();
    Optional<Transaction> getById(Long id);
    Transaction save(Transaction transaction);
    List<Transaction> getByAccountId(Long accountId);
}