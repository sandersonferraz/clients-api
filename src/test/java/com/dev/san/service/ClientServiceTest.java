package com.dev.san.service;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.model.entity.Client;
import com.dev.san.model.repository.ClientRepository;
import com.dev.san.util.ClientCreator;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.when;

@Slf4j
@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepository clientRepositoryMock;

    @BeforeEach
    void setUp(){
        ArrayList clientsList = new ArrayList<>();
        clientsList.add(ClientCreator.createValidClient());
        Integer clientId = ClientCreator.createValidClient().getId();
        String clientCPF = ClientCreator.createValidClient().getCpf();

        when(clientRepositoryMock.findAll()).thenReturn(clientsList);
        when(clientRepositoryMock.findById(clientId))
                .thenReturn(Optional.ofNullable(ClientCreator.createValidClient()));
        when(clientRepositoryMock.findByCpf(clientCPF))
                .thenReturn(ClientCreator.createValidClient());
        when(clientRepositoryMock.save(ArgumentMatchers.any(Client.class)))
                .thenReturn(ClientCreator.createValidClient());
        doNothing().when(clientRepositoryMock).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("save returns a clients when successful.")
    void saveReturnsClientWhenSuccessful(){
        final ClientPostDto clientToBeSave = ClientCreator.createClientToBeSave();
        Client clientSaved = clientService.save(clientToBeSave);
        Assertions.assertThat(clientSaved).isNotNull();
        Assertions.assertThat(clientSaved.getCpf()).isEqualTo(clientToBeSave.getCpf());
        Assertions.assertThat(clientSaved.getFullName()).isEqualTo(clientToBeSave.getFullName());
    }

    @Test
    @DisplayName("FindAll returns a list of Clients when successful.")
    void findAllReturnsListClientsWhenSuccessful(){
        String expectedFullName = ClientCreator.createValidClient().getFullName();
        List<Client> clients = clientService.findAllNonPageable();
        Assertions.assertThat(clients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clients.get(0).getFullName()).isEqualTo(expectedFullName);
    }

    @Test
    @DisplayName("FIndById Returns Clint when Successful.")
    void findByIdReturnsClientWhenSuccessful(){
        Integer expectedClientId = ClientCreator.createValidClient().getId();
        Client client = clientService.findById(expectedClientId);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getId()).isNotNull().isEqualTo(expectedClientId);
    }

    @Test
    @DisplayName("FindByCPF return Client when successful.")
    void findByCpfReturnsClientWhenSuccessful(){
        String expectedClientCPF = ClientCreator.createValidClient().getCpf();
        Client client = clientService.findByCpf(expectedClientCPF);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getCpf()).isNotNull().isEqualTo(expectedClientCPF);
    }

    @Test
    @DisplayName("update returns a client when successful.")
    void updateReturnsClientWhenSuccessful() {
        Client clientUpdated = clientService.replace(ClientCreator.updateValidClient());
        Assertions.assertThat(clientUpdated).isNotNull()
                .isEqualTo(ClientCreator.createValidClient());
    }

    @Test
    @DisplayName("delete removes client when successful.")
    void deleteRemovesClientWhenSuccessful() {
        Client expectedClient = ClientCreator.createValidClient();
        Assertions.assertThatCode(()-> clientService.delete(expectedClient.getId()))
                .doesNotThrowAnyException();
    }



}
