package com.dev.san.model.repository;

import com.dev.san.model.entity.Client;
import com.dev.san.model.entity.ServicesProvided;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.Random;

import static com.dev.san.util.ClientCreator.createValidClient;
import static com.dev.san.util.ServiceProvidedCreator.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
@DataJpaTest
@DisplayName("Tests for Services Provided repository")
class ServicesProvidedRepositoryTest {

    private static Random random = new Random();
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceProvidedRepository serviceProvidedRepository;



    @Test
    @DisplayName("Save persists Services Provided when successful")
    void savePersistServicesProvidedWhenSuccessful() {
        final Client clientToBeSave = createValidClient();
        clientToBeSave.setId(1);
        log.info("Client to save: {}", clientToBeSave);
        final Client clientSaved = this.clientRepository.save(clientToBeSave);
        log.info("Client saved: {}", clientSaved);
        final ServicesProvided servicesProvided = createValidService();
        servicesProvided.setClient(clientSaved);
        log.info("services Provided to save: {}", servicesProvided);
        ServicesProvided servicesProvidedSaved = this.serviceProvidedRepository.save(servicesProvided);
        log.info("services Provided saved: {}", servicesProvidedSaved);
        assertThat(servicesProvidedSaved).isNotNull();
        assertThat(servicesProvidedSaved.getServiceDescription()).isEqualTo(servicesProvidedSaved.getServiceDescription());
        assertThat(servicesProvidedSaved.getClient()).isEqualTo(clientSaved);
    }

    @Test
    @DisplayName("Delete removes ServicesProvided when successful")
    void deleteRemovesServicesProvidedWhenSuccessful() {
        final Client clientToBeSave = createValidClient();
        clientToBeSave.setId(1);
        log.info("Client to save: {}", clientToBeSave);
        final Client clientSaved = this.clientRepository.save(clientToBeSave);
        log.info("Client saved: {}", clientSaved);
        final ServicesProvided servicesProvided = createValidService();
        servicesProvided.setClient(clientSaved);
        log.info("services Provided to save: {}", servicesProvided);
        ServicesProvided servicesProvidedToDelete = this.serviceProvidedRepository.save(servicesProvided);
        this.serviceProvidedRepository.delete(servicesProvidedToDelete);
        Optional<ServicesProvided> servicesProvidedOptional = this.serviceProvidedRepository.findById(servicesProvided.getId());
        assertThat(servicesProvidedOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by ID return null when no ServicesProvided is found")
    void findByIDReturnNullWhenNoServicesProvidedIsNotFound()  {
        Optional<ServicesProvided> client = this.serviceProvidedRepository.findById(random.nextInt(200));
        assertThat(client).isNotPresent();

    }

}
