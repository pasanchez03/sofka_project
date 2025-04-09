package com.alejandrosanchez.account.controller;

import com.alejandrosanchez.account.dto.AccountReportDTO;
import com.alejandrosanchez.account.model.Account;
import com.alejandrosanchez.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuentas")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findByActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> account = accountService.getByIdAndActiveTrue(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid Account account) {
        Account savedClient = accountService.save(account);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody @Valid Account account) {
        Optional<Account> updatedAccount = accountService.update(id, account);
        return updatedAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeAccountStatus(@PathVariable Long id, @RequestParam boolean active) {
        boolean updated = accountService.changeStatus(id, active);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/reportes")
    public ResponseEntity<AccountReportDTO> getAccountReport(
            @RequestParam("clienteId") Long clientId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        AccountReportDTO report = accountService.getAccountReport(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}