package com.dev.san.model.repository;

import com.dev.san.model.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static com.dev.san.util.ClientCreator.createClientToBeSave;


@Slf4j
@DataJpaTest
@DisplayName("Tests for client repository")
class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Save persists client when successful")
    void savePersistClientWhenSuccessful() {
        Client clientToBeSaved = createClientToBeSave();
        Client clientSaved = this.clientRepository.save(clientToBeSaved);
        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getFullName()).isEqualTo(clientToBeSaved.getFullName());
    }

    @Test
    @DisplayName("Save updates client when successful")
    void saveUpdatesClientWhenSuccessful() {
        Client clientToBeSaved = createClientToBeSave();
        Client clientSaved = this.clientRepository.save(clientToBeSaved);
        clientSaved.setFullName("Walter Bruce Willis");
        Client clientUpdated = this.clientRepository.save(clientSaved);
        log.info("[>] Client name updated, {}", clientUpdated.getFullName());
        Assertions.assertThat(clientUpdated).isNotNull();
        Assertions.assertThat(clientUpdated.getId()).isNotNull();
        Assertions.assertThat(clientUpdated.getFullName()).isEqualTo(clientSaved.getFullName());
    }

    @Test
    @DisplayName("Delete removes client when successful")
    void deleteRemovesClientWhenSuccessful() {
        Client clientToBeSaved = createClientToBeSave();
        Client clientSaved = this.clientRepository.save(clientToBeSaved);
        this.clientRepository.delete(clientSaved);
        Optional<Client> clientOptional = this.clientRepository.findById(clientSaved.getId());
        Assertions.assertThat(clientOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by cpf return client when successful")
    void findByCpfReturnClientWhenSuccessful() {
        Client clientToBeSaved = createClientToBeSave();
        Client clientSaved = this.clientRepository.save(clientToBeSaved);
        Client client = this.clientRepository.findByCpf(clientSaved.getCpf());
        Assertions.assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("Find by ID return null when no client is found")
    void findByIDReturnNullWhenNoClientIsNotFound() {
        Optional<Client> client = this.clientRepository.findById(UUID.randomUUID());
        Assertions.assertThat(client).isNotPresent();
    }

    @Test
    @DisplayName("Find by cpf return null when no client is found")
    void findByCpfReturnNullWhenNoClientIsNotFound() {
        Client client = this.clientRepository.findByCpf("abcdefghijl");
        Assertions.assertThat(client).isNull();
    }


}
