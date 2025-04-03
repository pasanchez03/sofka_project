package com.alejandrosanchez.account.messaging;

import com.alejandrosanchez.account.service.AccountService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientStatusConsumer {

    private final AccountService accountService;

    public ClientStatusConsumer(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = "client-status-topic", groupId = "account-service-group")
    public void listenClientStatusUpdate(String message) {
        String[] data = message.split(",");
        Long clientId = Long.parseLong(data[0]);
        boolean active = Boolean.parseBoolean(data[1]);

        accountService.updateAccountStatusByClientId(clientId, active);
    }
}
