package com.alejandrosanchez.client.service;

import com.alejandrosanchez.client.model.Client;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findByActiveTrue();
    Optional<Client> getByIdAndActiveTrue(Long id);
    Client save(Client client);
    void delete(Long id);
    boolean changeStatus(Long id, boolean active);
}