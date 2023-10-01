package com.dev.san.util;

import com.dev.san.model.entity.ServicesProvided;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import static com.dev.san.util.ClientCreator.createValidClient;

public class ServiceProvidedCreator {
    private static Random random = new Random();

    private static final Integer ID_SERVICE = random.nextInt(100);;
    private static final LocalDate LOCAL_DATE = LocalDate.now();

    public static ServicesProvided createValidService() {
        return ServicesProvided.builder()
                .serviceDescription("Developer E-Commerce to supermarket.")
                .client(createValidClient())
                .serviceValue(new BigDecimal("30000.00"))
                .id(ID_SERVICE)
                .build();
    }

    public static ServicesProvided createServiceToBeReplace() {
        return ServicesProvided.builder()
                .serviceDescription("Developer Project System ")
                .client(createValidClient())
                .serviceValue(new BigDecimal("10000.00"))
                .id(ID_SERVICE)
                .build();
    }

}
