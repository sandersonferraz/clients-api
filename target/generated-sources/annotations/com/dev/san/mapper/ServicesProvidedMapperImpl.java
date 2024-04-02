package com.dev.san.mapper;

import com.dev.san.dto.ServicesProvidedPostDto;
import com.dev.san.dto.ServicesProvidedPutDto;
import com.dev.san.model.entity.ServicesProvided;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-25T00:00:26-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Private Build)"
)
public class ServicesProvidedMapperImpl implements ServicesProvidedMapper {

    @Override
    public ServicesProvided toServiceProvided(ServicesProvidedPostDto servicesProvidedPostDto) {
        if ( servicesProvidedPostDto == null ) {
            return null;
        }

        ServicesProvided.ServicesProvidedBuilder servicesProvided = ServicesProvided.builder();

        servicesProvided.serviceDescription( servicesProvidedPostDto.getServiceDescription() );
        servicesProvided.client( servicesProvidedPostDto.getClient() );
        servicesProvided.serviceValue( servicesProvidedPostDto.getServiceValue() );

        return servicesProvided.build();
    }

    @Override
    public ServicesProvidedPostDto toServicesProvidedPostDto(ServicesProvided servicesProvided) {
        if ( servicesProvided == null ) {
            return null;
        }

        ServicesProvidedPostDto.ServicesProvidedPostDtoBuilder servicesProvidedPostDto = ServicesProvidedPostDto.builder();

        servicesProvidedPostDto.serviceDescription( servicesProvided.getServiceDescription() );
        servicesProvidedPostDto.client( servicesProvided.getClient() );
        servicesProvidedPostDto.serviceValue( servicesProvided.getServiceValue() );

        return servicesProvidedPostDto.build();
    }

    @Override
    public ServicesProvided toServiceProvided(ServicesProvidedPutDto servicesProvidedPutDto) {
        if ( servicesProvidedPutDto == null ) {
            return null;
        }

        ServicesProvided.ServicesProvidedBuilder servicesProvided = ServicesProvided.builder();

        servicesProvided.id( servicesProvidedPutDto.getId() );
        servicesProvided.serviceDescription( servicesProvidedPutDto.getServiceDescription() );
        servicesProvided.client( servicesProvidedPutDto.getClient() );
        servicesProvided.serviceValue( servicesProvidedPutDto.getServiceValue() );

        return servicesProvided.build();
    }

    @Override
    public ServicesProvidedPutDto toServicesProvidedPutDto(ServicesProvided servicesProvided) {
        if ( servicesProvided == null ) {
            return null;
        }

        ServicesProvidedPutDto.ServicesProvidedPutDtoBuilder servicesProvidedPutDto = ServicesProvidedPutDto.builder();

        servicesProvidedPutDto.id( servicesProvided.getId() );
        servicesProvidedPutDto.serviceDescription( servicesProvided.getServiceDescription() );
        servicesProvidedPutDto.client( servicesProvided.getClient() );
        servicesProvidedPutDto.serviceValue( servicesProvided.getServiceValue() );

        return servicesProvidedPutDto.build();
    }
}
