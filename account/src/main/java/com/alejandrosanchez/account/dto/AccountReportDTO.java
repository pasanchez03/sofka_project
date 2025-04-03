package com.alejandrosanchez.account.dto;

import java.util.List;

public class AccountReportDTO {
    private Long clientId;
    private List<AccountDetailsDTO> accounts;

    public AccountReportDTO(Long clientId, List<AccountDetailsDTO> accounts) {
        this.clientId = clientId;
        this.accounts = accounts;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<AccountDetailsDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDetailsDTO> accounts) {
        this.accounts = accounts;
    }
}

