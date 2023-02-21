package com.dev.san.util;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;


public class ClientCreator {
   private static Random random = new Random();

    private static final Integer ID_CLIENT = random.nextInt(100);
    private static final LocalDate LOCAL_DATE = LocalDate.now();

    public static Client createClientToBeSave() {
        return Client.builder()
                .fullName("Bruce Willis")
                .cpf("00000000000")
                .serviceDate(LOCAL_DATE)
                .build();
    }

    public static Client createValidClient() {
        return Client.builder()
                .fullName("Mr Smith")
                .cpf("00000000001")
                .id(ID_CLIENT)
                .serviceDate(LOCAL_DATE)
                .build();
    }

    public static Client updateValidClient() {
        return Client.builder()
                .fullName("Michael w. Smith")
                .cpf("00000000002")
                .id(ID_CLIENT)
                .serviceDate(LOCAL_DATE)
                .build();
    }

    public static ClientDto convert(Client entity){
        ClientDto dto = new ClientDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
