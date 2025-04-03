package com.alejandrosanchez.account.config;

import com.alejandrosanchez.account.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url = "${CLIENT_API_URL}")
public interface ClientServiceClient {
    @GetMapping("/{clientId}")
    ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId);
}