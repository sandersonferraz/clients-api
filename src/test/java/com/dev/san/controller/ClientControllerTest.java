package com.dev.san.controller;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import com.dev.san.service.ClientService;
import com.dev.san.util.ClientCreator;
import lombok.var;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientService clientServiceMock;

    @BeforeEach
    void setUp(){
        ArrayList clientsList = new ArrayList();
        clientsList.add(ClientCreator.createValidClient());
        when(clientServiceMock.listAll()).thenReturn(clientsList);
        UUID clientId = ClientCreator.createValidClient().getId();
        when(clientServiceMock.findById(clientId)).thenReturn(ClientCreator.createValidClient());
        when(clientServiceMock.save(ClientCreator.createValidClient())).thenReturn(ClientCreator.createValidClient());
        doNothing().when(clientServiceMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("ListAll returns a list of clients when successful.")
    void listAllReturnsListOfClientsWhenSuccessful(){
        String expectedFullName = ClientCreator.createValidClient().getFullName();
        List<Client> clients = clientController.listAll();
        Assertions.assertThat(clients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clients.get(0).getFullName()).isEqualTo(expectedFullName);
    }

    @Test
    @DisplayName("FindById returns a clients when successful.")
    void findByIdReturnsClientWhenSuccessful() throws Exception {
        UUID expectedId = ClientCreator.createValidClient().getId();
        var client = clientController.findById(expectedId);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("save returns a clients when successful.")
    void saveReturnsClientWhenSuccessful(){
        ClientDto clientSaved = clientController.save(ClientCreator.createValidClientDto());
        Assertions.assertThat(clientSaved).isNotNull().isEqualTo(ClientCreator.createValidClientDto());
    }

    @Test
    @DisplayName("update returns a client when successful.")
    void updateReturnsClientWhenSuccessful() {
        ClientDto clientUpdated = clientController.save(ClientCreator.updateValidClientDto());
        Assertions.assertThat(clientUpdated).isNotNull().isEqualTo(ClientCreator.updateValidClientDto());
    }

    @Test
    @DisplayName("delete removes client when successful.")
    void deleteRemovesClientWhenSuccessful() {
        UUID expectedId = ClientCreator.createValidClient().getId();
        Assertions.assertThatCode(()-> clientController.delete(expectedId))
                .doesNotThrowAnyException();
    }

}