package com.alejandrosanchez.client.integration;

import com.alejandrosanchez.client.model.Client;
import com.alejandrosanchez.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    private Client testClient;

    @BeforeEach
    void setup() {
        clientRepository.deleteAll();

        testClient = new Client();
        testClient.setName("Alejandro Sanchez");
        testClient.setGender("Masculino");
        testClient.setBirthDate(LocalDate.of(1996, 9, 3));
        testClient.setIdentification("1234567890");
        testClient.setAddress("ABC N12-34");
        testClient.setPhone("023456789");
        testClient.setClientId("C12345");
        testClient.setMail("a@fake.com");
        testClient.setPassword("contraseña123");
        testClient.setActive(true);

        clientRepository.save(testClient);
    }

    @Test
    void testCreateClient() throws Exception {
        String newClientJson = """
        {
           "nombre": "Alejandro Sanchez",
           "genero": "Masculino",
           "fechaNacimiento": "1996-09-03",
           "identificacion": "123456789",
           "direccion": "ABC N12-34",
           "telefono": "023456789",
           "clienteId": "CL12345",
           "correo": "abc@fake.com",
           "contrasena": "contraseña123"
         }
        """;

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newClientJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Alejandro Sanchez"))
                .andExpect(jsonPath("$.identificacion").value("123456789"));
    }

    @Test
    void testGetClientById() throws Exception {
        mockMvc.perform(get("/api/clientes/{id}", testClient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Alejandro Sanchez"))
                .andExpect(jsonPath("$.identificacion").value("1234567890"));
    }

    @Test
    void testUpdateClient() throws Exception {
        String updatedClientJson = """
            {
              "nombre": "Alejandro Sanchez Modificado",
              "genero": "M",
              "fechaNacimiento": "1996-09-03",
              "identificacion": "12345",
              "direccion": "ABCN12-34",
              "telefono": "02345678",
              "clienteId": "C123",
              "correo": "1@fake.com",
              "contrasena": "contrasena123"
            }
        """;

        mockMvc.perform(put("/api/clientes/{id}", testClient.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedClientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Alejandro Sanchez Modificado"))
                .andExpect(jsonPath("$.direccion").value("ABCN12-34"));
    }

    @Test
    void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/api/clientes/{id}", testClient.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/clientes/{id}", testClient.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testPatchClient() throws Exception {
        mockMvc.perform(patch("/api/clientes/{id}/status?active=true", testClient.getId()))
                .andExpect(status().isOk());
    }
}
