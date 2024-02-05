package com.dev.san.service;

import com.dev.san.dto.ServicesProvidedPostDto;
import com.dev.san.dto.ServicesProvidedPutDto;
import com.dev.san.excption.BadRequestException;
import com.dev.san.mapper.ServicesProvidedMapper;
import com.dev.san.model.entity.ServicesProvided;
import com.dev.san.model.repository.ServiceProvidedRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesProvidedService {
    private static final String NOT_FOUND = "Service Provided was not found.";

    private final ServiceProvidedRepository serviceProvidedRepository;


    public ServicesProvided save(ServicesProvidedPostDto servicesProvidedPostDto){
        return serviceProvidedRepository
                .save(ServicesProvidedMapper.INSTANCE.toServiceProvided(servicesProvidedPostDto));
    }

    public List<ServicesProvided> findAllNoPageable(){
        return  this.serviceProvidedRepository.findAll();
    }

    public Page<ServicesProvided> findAll(Pageable pageable){
        return  this.serviceProvidedRepository.findAll(pageable);
    }

    public ServicesProvided findById(Integer id){
        return serviceProvidedRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND));
    }

    public ServicesProvided replace(ServicesProvidedPutDto servicesProvidedPutDto){
        ServicesProvided servicesProvided = this.findById(servicesProvidedPutDto.getId());
        servicesProvided.setServiceDescription(servicesProvidedPutDto.getServiceDescription());
        servicesProvided.setServiceValue(servicesProvidedPutDto.getServiceValue());
        servicesProvided.setClient(servicesProvidedPutDto.getClient());
        return this.serviceProvidedRepository.save(servicesProvided);
    }

    public void delete(Integer id){
        ServicesProvided servicesProvided = serviceProvidedRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(NOT_FOUND));
        serviceProvidedRepository.delete(servicesProvided);

    }


}
