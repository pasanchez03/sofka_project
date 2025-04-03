package com.alejandrosanchez.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Person {

    @Column(unique = true, nullable = false)
    @JsonProperty("clienteId")
    private String clientId;

    @Column(unique = true, nullable = false)
    @JsonProperty("correo")
    private String mail;

    @Column(nullable = false)
    @JsonProperty("contrasena")
    private String password;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}