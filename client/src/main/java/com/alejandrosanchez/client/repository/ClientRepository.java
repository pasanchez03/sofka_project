package com.alejandrosanchez.client.repository;

import com.alejandrosanchez.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByActiveTrue();
    Optional<Client> findByIdAndActiveTrue(Long id);
}