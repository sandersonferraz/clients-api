package com.dev.san.controller;

import com.dev.san.dto.ClientDto;
import com.dev.san.service.ClientService;
import com.dev.san.util.ClientCreator;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@Slf4j
class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;
    @Mock
    private ClientService clientServiceMock;



    @BeforeEach
    void setUp() {
        PageImpl<ClientDto> clientDtoPage = new PageImpl<>(List.of(ClientCreator.convert(ClientCreator.createValidClient())));
        when(clientServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(clientDtoPage);
        when(clientServiceMock.findAllNonPageable())
                .thenReturn(List.of(ClientCreator.convert(ClientCreator.createValidClient())));
        when(clientServiceMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(ClientCreator.convert(ClientCreator.createValidClient()));
        when(clientServiceMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(ClientCreator.convert(ClientCreator.createValidClient()));
        when(clientServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(ClientCreator.convert(ClientCreator.createValidClient()));
        doNothing().when(clientServiceMock).delete(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("ListAll returns a list of clients when successful.")
    void listAllReturnsListOfClientsWhenSuccessful() {
        String expectedFullName = ClientCreator.convert(ClientCreator.createValidClient()).getFullName();
        Page<ClientDto> clientsPage = clientController.listAll(null).getBody();
        Assertions.assertThat(clientsPage).isNotNull();
        Assertions.assertThat(clientsPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clientsPage.toList().get(0).getFullName())
                .isEqualTo(expectedFullName);
    }

    @Test
    @DisplayName("FindById returns a clients when successful.")
    void findByIdReturnsClientWhenSuccessful() {
        Integer expectedId = ClientCreator.convert(ClientCreator.createValidClient()).getId();
        var client = clientController.findById(expectedId);
        Assertions.assertThat(client).isNotNull();
        Assertions.assertThat(client.getBody().getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("FindByCPF returns a clients when successful.")
    void findByCPFReturnsClientWhenSuccessful() {
        String expectedCPF = ClientCreator.convert(ClientCreator.createValidClient()).getCpf();
        var clientDto = clientController.findByCpf(expectedCPF);
        Assertions.assertThat(clientDto).isNotNull();
        Assertions.assertThat(clientDto.getBody().getCpf()).isNotNull().isEqualTo(expectedCPF);
    }

    @Test
    @DisplayName("save returns a clients when successful.")
    void saveReturnsClientWhenSuccessful() {
        var clientSaved = clientController.save(ClientCreator.convert(ClientCreator.createValidClient())).getBody();
        Assertions.assertThat(clientSaved).isNotNull()
                .isEqualTo(ClientCreator.convert(ClientCreator.createValidClient()));
    }

    @Test
    @DisplayName("update returns a client when successful.")
    void updateReturnsClientWhenSuccessful() {
        var clientToBeUpdated = ClientCreator.convert(ClientCreator.updateValidClient());
        ClientDto clientUpdated = clientController.update(clientToBeUpdated.getId(), clientToBeUpdated).getBody();
        clientUpdated.setCpf(clientToBeUpdated.getCpf());
        clientUpdated.setFullName(clientToBeUpdated.getFullName());
        Assertions.assertThat(clientUpdated)
                .isNotNull().isEqualTo(clientToBeUpdated);
    }

    @Test
    @DisplayName("delete removes client when successful.")
    void deleteRemovesClientWhenSuccessful() {
        Integer expectedId = ClientCreator.convert(ClientCreator.createValidClient()).getId();
        Assertions.assertThatCode(() -> clientController.delete(expectedId))
                .doesNotThrowAnyException();
    }

}