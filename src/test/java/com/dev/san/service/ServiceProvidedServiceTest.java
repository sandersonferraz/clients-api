package com.dev.san.service;

import com.dev.san.mapper.ServicesProvidedMapper;
import com.dev.san.model.entity.ServicesProvided;
import com.dev.san.model.repository.ServiceProvidedRepository;
import org.junit.jupiter.api.AfterEach;
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

import static com.dev.san.util.ServiceProvidedCreator.createValidService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ServiceProvidedServiceTest {

    @InjectMocks
    private ServicesProvidedService serviceProvidedService;

    @Mock
    private ServiceProvidedRepository serviceProvidedRepositoryMock;

    @BeforeEach
    void setUp(){
        List<ServicesProvided> servicesProvides = new ArrayList<>();
        servicesProvides.add(createValidService());
        when(serviceProvidedRepositoryMock.findAll())
                .thenReturn(servicesProvides);
        when(serviceProvidedRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(createValidService()));
        when(serviceProvidedRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(createValidService());

    }

    @AfterEach
    void down(){

    }

    @Test
    @DisplayName("Save ServiceProvided returns Service Provided when successful.")
    void saveServiceProvidedReturnsServiceProvidedWhenSuccessful(){
        final ServicesProvided servicesProvided = createValidService();
        ServicesProvided serviceProvidedSaved = serviceProvidedService.save(ServicesProvidedMapper.INSTANCE.toServicesProvidedPostDto(servicesProvided));
        assertThat(serviceProvidedSaved).isNotNull()
                .isEqualTo(servicesProvided);
    }

    @Test
    @DisplayName("Update ServiceProvided returns Service Provided when successful.")
    void updateServiceProvidedReturnsServiceProvidedWhenSuccessful(){
        final ServicesProvided servicesProvided = createValidService();
        ServicesProvided serviceProvidedReplaced = serviceProvidedService.replace(ServicesProvidedMapper.INSTANCE.toServicesProvidedPutDto(servicesProvided));
        assertThat(serviceProvidedReplaced).isNotNull()
                .isEqualTo(servicesProvided);
    }




}
