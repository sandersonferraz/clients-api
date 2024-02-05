package com.dev.san.util;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.dto.ClientPutDto;
import com.dev.san.model.entity.Client;

import java.time.LocalDate;
import java.util.Random;



public class ClientCreator {
   private static Random random = new Random();

    private static final Integer ID_CLIENT = random.nextInt(100);
    private static final LocalDate LOCAL_DATE = LocalDate.now();

    public static ClientPostDto createClientToBeSave() {
        return ClientPostDto.builder()
                .fullName("Bruce Willis")
                .cpf("00000000001")
                .build();
    }

    public static Client createValidClient() {
        return Client.builder()
                .fullName("Bruce Willis")
                .cpf("00000000001")
                .id(ID_CLIENT)
                .serviceDate(LOCAL_DATE)
                .build();
    }

    public static ClientPutDto updateValidClient() {
        return ClientPutDto.builder()
                .fullName("Michael w. Smith")
                .cpf("00000000003")
                .id(ID_CLIENT)
                .serviceDate(LOCAL_DATE)
                .build();
    }

}
