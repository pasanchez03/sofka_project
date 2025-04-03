package com.alejandrosanchez.client.domain;
import com.alejandrosanchez.client.model.Client;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

class ClientTest {

    @Test
    void testClientCreation() {
        Client client = new Client();
        client.setName("Alejandro Sanchez");
        client.setGender("Masculino");
        client.setBirthDate(LocalDate.of(1996, 9, 3));
        client.setIdentification("123456789");
        client.setAddress("ABC N12-34");
        client.setPhone("023456789");
        client.setClientId("C12345");
        client.setMail("a@fake.com");
        client.setPassword("contraseña123");
        client.setActive(true);

        assertThat(client.getName()).isEqualTo("Alejandro Sanchez");
        assertThat(client.getGender()).isEqualTo("Masculino");
        assertThat(client.getBirthDate()).isEqualTo(LocalDate.of(1996, 9, 3));
        assertThat(client.getIdentification()).isEqualTo("123456789");
        assertThat(client.getAddress()).isEqualTo("ABC N12-34");
        assertThat(client.getPhone()).isEqualTo("023456789");
        assertThat(client.getClientId()).isEqualTo("C12345");
        assertThat(client.getMail()).isEqualTo("a@fake.com");
        assertThat(client.isActive()).isTrue();
    }
}