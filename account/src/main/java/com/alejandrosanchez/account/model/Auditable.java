package com.alejandrosanchez.account.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditable {

    @Column(nullable = false)
    @JsonProperty("estado")
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    @JsonProperty("fechaCreacion")
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @JsonProperty("fechaModificacion")
    private LocalDateTime modificationDate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        modificationDate = creationDate;
    }

    @PreUpdate
    protected void onUpdate() {
        modificationDate = LocalDateTime.now();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}