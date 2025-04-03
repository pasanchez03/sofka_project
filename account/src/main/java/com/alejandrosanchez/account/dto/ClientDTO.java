package com.alejandrosanchez.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientDTO {
    private Long id;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("identificacion")
    private String identification;
    @JsonProperty("estado")
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
