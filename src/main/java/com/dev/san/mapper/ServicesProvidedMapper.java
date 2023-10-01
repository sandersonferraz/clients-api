package com.dev.san.mapper;

import com.dev.san.dto.ServicesProvidedPostDto;
import com.dev.san.dto.ServicesProvidedPutDto;
import com.dev.san.model.entity.ServicesProvided;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ServicesProvidedMapper {
    ServicesProvidedMapper INSTANCE = Mappers.getMapper(ServicesProvidedMapper.class);
    ServicesProvided toServiceProvided(ServicesProvidedPostDto servicesProvidedPostDto);
    ServicesProvidedPostDto toServicesProvidedPostDto(ServicesProvided servicesProvided);
    ServicesProvided toServiceProvided(ServicesProvidedPutDto servicesProvidedPutDto);
    ServicesProvidedPutDto toServicesProvidedPutDto(ServicesProvided servicesProvided);

}
