package com.dev.san.model.repository;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.model.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Random;

import static com.dev.san.util.ClientCreator.createClientToBeSave;
import static com.dev.san.util.ClientCreator.createValidClient;


@Slf4j
@DataJpaTest
@DisplayName("Tests for client repository")
class ClientRepositoryTest {
    private static Random random = new Random();
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Save persists client when successful")
    void savePersistClientWhenSuccessful() {
        final Client clientToBeSave = createValidClient();
        Client clientSaved = this.clientRepository.save(clientToBeSave);
        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getFullName()).isEqualTo(clientToBeSave.getFullName());
    }

    @Test
    @DisplayName("Delete removes client when successful")
    void deleteRemovesClientWhenSuccessful() {
        final Client clientToBeSave = createValidClient();
        Client clientSaved = this.clientRepository.save(clientToBeSave);
        this.clientRepository.delete(clientSaved);
        Optional<Client> clientOptional = this.clientRepository.findById(clientSaved.getId());
        Assertions.assertThat(clientOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by cpf return client when successful")
    void findByCpfReturnClientWhenSuccessful() {
        final Client clientToBeSave = createValidClient();
        Client clientSaved = this.clientRepository.save(clientToBeSave);
        Client client = this.clientRepository.findByCpf(clientSaved.getCpf());
        Assertions.assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("Find by ID return null when no client is found")
    void findByIDReturnNullWhenNoClientIsNotFound() {
        Optional<Client> client = this.clientRepository.findById(random.nextInt(200));
        Assertions.assertThat(client).isNotPresent();
    }

    @Test
    @DisplayName("Find by ID return null when no client is found")
    void findByIdReturnNullWhenNoClientIsNotFound() {
        Client client = this.clientRepository.findByCpf("abcdefghijl");
        Assertions.assertThat(client).isNull();
    }

}
