package com.dev.san.service;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.repository.ClientRepository;
import com.dev.san.util.ClientCreator;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.*;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        when(clientRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(ClientCreator.createValidClient());
        doNothing().when(clientRepositoryMock).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("FindAll returns a list of CLients when successful.")
    void findAllReturnsListClientsWhenSuccessful(){
        String expectedFullName = ClientCreator.createValidClient().getFullName();
        List<ClientDto> clients = clientService.findAllNonPageable();
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
        ClientDto client = clientService.findById(expectedClientId);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getId()).isNotNull().isEqualTo(expectedClientId);
    }

    @Test
    @DisplayName("FindByCPF return Client when successful.")
    void findByCpfReturnsClientWhenSuccessful(){
        String expectedClientCPF = ClientCreator.createValidClient().getCpf();
        ClientDto client = clientService.findByCpf(expectedClientCPF);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getCpf()).isNotNull().isEqualTo(expectedClientCPF);
    }

    @Test
    @DisplayName("save returns a clients when successful.")
    void saveReturnsClientWhenSuccessful(){
        ClientDto clientSaved = clientService.save(ClientCreator.convert(ClientCreator.createValidClient()));
        Assertions.assertThat(clientSaved).isNotNull().isEqualTo(ClientCreator.convert(ClientCreator.createValidClient()));
    }

    @Test
    @DisplayName("update returns a client when successful.")
    void updateReturnsClientWhenSuccessful() {
        ClientDto clientUpdated = clientService.save(ClientCreator.convert(ClientCreator.createValidClient()));
        Assertions.assertThat(clientUpdated).isNotNull()
                .isEqualTo(ClientCreator.convert(ClientCreator.createValidClient()));
    }

    @Test
    @DisplayName("delete removes client when successful.")
    void deleteRemovesClientWhenSuccessful() {
        ClientDto expectedClient = ClientCreator.convert(ClientCreator.createValidClient());
        Assertions.assertThatCode(()-> clientService.delete(expectedClient))
                .doesNotThrowAnyException();
    }



}
