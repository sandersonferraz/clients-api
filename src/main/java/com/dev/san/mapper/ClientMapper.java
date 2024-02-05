package com.dev.san.mapper;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.dto.ClientPutDto;
import com.dev.san.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toClient(ClientPostDto clientPostDto);

    ClientPostDto toClientPostDto(Client client);

    ClientPutDto toClientPutDto(Client client);

}
