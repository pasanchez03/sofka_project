package com.alejandrosanchez.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Person {

    @NotBlank(message = "El ID del cliente es obligatorio")
    @Size(max = 50, message = "El ID del cliente no puede tener más de 50 caracteres")
    @Column(unique = true, nullable = false)
    @JsonProperty("clienteId")
    private String clientId;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    @Column(unique = true, nullable = false)
    @JsonProperty("correo")
    private String mail;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
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