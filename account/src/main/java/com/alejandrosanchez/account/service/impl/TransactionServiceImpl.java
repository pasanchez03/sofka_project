package com.alejandrosanchez.account.service.impl;
import com.alejandrosanchez.account.model.Account;
import com.alejandrosanchez.account.model.Transaction;
import com.alejandrosanchez.account.repository.AccountRepository;
import com.alejandrosanchez.account.repository.TransactionRepository;
import com.alejandrosanchez.account.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction save(Transaction transaction) {
        Optional<Account> accountOpt = accountRepository.findById(transaction.getAccount().getId());

        if (accountOpt.isEmpty()) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        Account account = accountOpt.get();
        double newBalance = account.getInitialBalance() + transaction.getAmount();

        if (newBalance < 0) {
            throw new IllegalArgumentException("Saldo no disponible");
        }

        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        transaction.setDate(LocalDateTime.now());
        transaction.setBalance(newBalance);

        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}