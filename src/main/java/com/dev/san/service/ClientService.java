package com.dev.san.service;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import com.dev.san.model.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private static final String NOT_FOUND = "Client not found!";
    private ClientRepository clientRepository;


    @Transactional
    public ClientDto save(ClientDto clientDto) {
        Client client = new Client();
        return clientDto.convert(clientRepository.save(client.convert(clientDto)));
    }

    public List<ClientDto> findAllNonPageable() {
        ClientDto clientDto = new ClientDto();
        return clientDto.convertList(this.clientRepository.findAll());
    }

    public Page<ClientDto> listAll(Pageable pageable) {
        ClientDto clientDto = new ClientDto();
        return clientDto.convertPage(this.clientRepository.findAll(pageable));
    }

    public ClientDto findById(int id) {
        Optional<Client> clientOptional = this.clientRepository.findById(id);
        if (!clientOptional.isPresent())
            throw new IllegalArgumentException(NOT_FOUND);
        ClientDto clientDto = new ClientDto();
        return clientDto.convert(clientOptional.get());
    }

    public void delete(ClientDto clientDto) {
        Client client = new Client();
        this.clientRepository.delete(client.convert(clientDto));
    }

    public ClientDto findByCpf(String cpf) {
        Client client = this.clientRepository.findByCpf(cpf);
        if (Objects.isNull(client))
            throw new IllegalArgumentException(NOT_FOUND);
        ClientDto clientDto = new ClientDto();
        return clientDto.convert(client);
    }


}
