package com.dev.san.util;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class ClientCreator {

    private static final UUID ID_CLIENT = UUID.randomUUID();

    public static Client createClientToBeSave() {
        return Client.builder()
                .fullName("Bruce Willis")
                .cpf("22222222222")
                .build();
    }

    public static Client createValidClient() {
        return Client.builder()
                .fullName("Bruce Willis")
                .cpf("22222222222")
                .id(ID_CLIENT)
                .build();
    }




    public static Client createValidUpdatedClient() {
        return Client.builder()
                .fullName("Mr Smith")
                .cpf("89898989890")
                .id(ID_CLIENT)
                .build();
    }

    public static ClientDto createValidClientDto() {
        ClientDto clientDto = ClientDto.builder().build();
        BeanUtils.copyProperties(createValidClient(), clientDto);
        return clientDto;
    }

    public static ClientDto updateValidClientDto() {
        return ClientDto.builder()
                .fullName("Bruce Willis")
                .cpf("22222222222")
                .id(ID_CLIENT)
                .build();
    }

}
