package com.alejandrosanchez.account.service;

import com.alejandrosanchez.account.dto.AccountReportDTO;
import com.alejandrosanchez.account.model.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface AccountService {
    List<Account> findByActiveTrue();
    Optional<Account> getByIdAndActiveTrue(Long id);
    Account save(Account account);
    Optional<Account> update(Long id, Account account);
    boolean changeStatus(Long id, boolean active);
    AccountReportDTO getAccountReport(Long clientId, LocalDate startDate, LocalDate endDate);
    void updateAccountStatusByClientId(Long clientId, boolean active);
}