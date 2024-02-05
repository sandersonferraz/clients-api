package com.dev.san.service;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.dto.ClientPutDto;
import com.dev.san.excption.BadRequestException;
import com.dev.san.mapper.ClientMapper;
import com.dev.san.model.entity.Client;
import com.dev.san.model.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(ClientPostDto clientPostDto) {
        return clientRepository.save(ClientMapper.INSTANCE.toClient(clientPostDto));
    }

    public List<Client> findAllNonPageable() {
        return this.clientRepository.findAll();
       }

    public Page<Client> listAll(Pageable pageable) {
         return this.clientRepository.findAll(pageable);
    }

    public Client findById(int id) {
        Optional<Client> clientOptional = this.clientRepository.findById(id);
        if (!clientOptional.isPresent())
            throw new BadRequestException("{msg.not.found}");
        return clientOptional.get();
    }

    public void delete(Integer id) {
        final Client client = this.findById(id);
        this.clientRepository.delete(client);
    }

    public Client findByCpf(String cpf) {
        Client client = this.clientRepository.findByCpf(cpf);
        if (Objects.isNull(client))
            throw new BadRequestException("{msg.not.found}");
        return client;
    }


    public Client replace(ClientPutDto clientPutDto) {
        final Client client = this.findById(clientPutDto.getId());
        if(!client.getCpf().equals(clientPutDto.getCpf())) {
            client.setCpf(clientPutDto.getCpf());
        }
        if (!client.getFullName().equals(clientPutDto.getFullName())){
            client.setFullName(clientPutDto.getFullName());
        }
        return this.clientRepository.save(client);
    }
}
