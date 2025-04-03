package com.alejandrosanchez.account.service.impl;

import com.alejandrosanchez.account.config.ClientServiceClient;
import com.alejandrosanchez.account.dto.AccountDetailsDTO;
import com.alejandrosanchez.account.dto.AccountReportDTO;
import com.alejandrosanchez.account.dto.ClientDTO;
import com.alejandrosanchez.account.dto.TransactionDTO;
import com.alejandrosanchez.account.model.Account;
import com.alejandrosanchez.account.model.Transaction;
import com.alejandrosanchez.account.repository.AccountRepository;
import com.alejandrosanchez.account.repository.TransactionRepository;
import com.alejandrosanchez.account.service.AccountService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ClientServiceClient clientServiceClient;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
                              ClientServiceClient clientServiceClient) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.clientServiceClient = clientServiceClient;
    }

    @Override
    public List<Account> findByActiveTrue() {
        return accountRepository.findByActiveTrue();
    }

    @Override
    public Optional<Account> getByIdAndActiveTrue(Long id) {
        return accountRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public Account save(Account account) {
        try {
            ClientDTO client = clientServiceClient.getClientById(account.getClientId()).getBody();
            if (client == null || !client.getActive()) {
                throw new IllegalArgumentException("Cliente no encontrada");
            }
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado en client-service.");
        }
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> update(Long id, Account updatedAccount) {
        return getByIdAndActiveTrue(id).map(existingAccount -> {
            existingAccount.setAccountNumber(updatedAccount.getAccountNumber());
            existingAccount.setAccountType(updatedAccount.getAccountType());
            existingAccount.setInitialBalance(updatedAccount.getInitialBalance());

            return accountRepository.save(existingAccount);
        });
    }

    @Override
    public boolean changeStatus(Long id, boolean active) {
        return accountRepository.findById(id).map(account -> {
            account.setActive(active);
            accountRepository.save(account);
            return true;
        }).orElse(false);
    }

    @Override
    public AccountReportDTO getAccountReport(Long clientId, LocalDate startDate, LocalDate endDate) {
        List<Account> accounts = accountRepository.findByClientIdAndActiveTrue(clientId);
        if (accounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existen cuentas activas del cliente");
        }

        List<AccountDetailsDTO> accountDetailsList = accounts.stream().map(account -> {
            List<Transaction> transactions = transactionRepository.findByAccountIdAndDateBetween(
                    account.getId(), startDate.atStartOfDay(), endDate.atTime(23, 59, 59, 999999999));

            List<TransactionDTO> transactionDTOs = transactions.stream()
                    .map(tx -> new TransactionDTO(tx.getDate(), tx.getTransactionType(), tx.getAmount(), tx.getBalance()))
                    .collect(Collectors.toList());

            return new AccountDetailsDTO(
                    account.getAccountNumber(),
                    account.getAccountType(),
                    account.getInitialBalance(),
                    transactionDTOs
            );
        }).collect(Collectors.toList());

        return new AccountReportDTO(clientId, accountDetailsList);
    }

    @Override
    public void updateAccountStatusByClientId(Long clientId, boolean active) {
        List<Account> accounts = accountRepository.findByClientId(clientId);
        for (Account account : accounts) {
            account.setActive(active);
        }
        accountRepository.saveAll(accounts);
    }
}