package com.alejandrosanchez.client.service.impl;

import com.alejandrosanchez.client.messaging.ClientStatusProducer;
import com.alejandrosanchez.client.model.Client;
import com.alejandrosanchez.client.repository.ClientRepository;
import com.alejandrosanchez.client.service.ClientService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientStatusProducer clientStatusProducer;

    public ClientServiceImpl(ClientRepository clientRepository, ClientStatusProducer clientStatusProducer) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.clientStatusProducer = clientStatusProducer;
    }

    @Override
    public List<Client> findByActiveTrue() {
        return clientRepository.findByActiveTrue();
    }

    @Override
    public Optional<Client> getByIdAndActiveTrue(Long id) {
        return clientRepository.findByIdAndActiveTrue(id);
    }

    @Override
    public Client save(Client client) {
        String encryptedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encryptedPassword);

        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean changeStatus(Long id, boolean active) {
        return clientRepository.findById(id).map(client -> {
            client.setActive(active);
            clientRepository.save(client);
            clientStatusProducer.sendClientStatusUpdate(id, active);
            return true;
        }).orElse(false);
    }
}