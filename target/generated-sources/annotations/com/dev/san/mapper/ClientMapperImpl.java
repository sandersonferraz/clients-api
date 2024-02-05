package com.dev.san.mapper;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.dto.ClientPutDto;
import com.dev.san.model.entity.Client;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-05T09:04:38-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.21 (Ubuntu)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toClient(ClientPostDto clientPostDto) {
        if ( clientPostDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.fullName( clientPostDto.getFullName() );
        client.cpf( clientPostDto.getCpf() );

        return client.build();
    }

    @Override
    public ClientPostDto toClientPostDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientPostDto.ClientPostDtoBuilder clientPostDto = ClientPostDto.builder();

        clientPostDto.fullName( client.getFullName() );
        clientPostDto.cpf( client.getCpf() );

        return clientPostDto.build();
    }

    @Override
    public ClientPutDto toClientPutDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientPutDto.ClientPutDtoBuilder clientPutDto = ClientPutDto.builder();

        clientPutDto.id( client.getId() );
        clientPutDto.fullName( client.getFullName() );
        clientPutDto.cpf( client.getCpf() );
        clientPutDto.serviceDate( client.getServiceDate() );

        return clientPutDto.build();
    }
}
