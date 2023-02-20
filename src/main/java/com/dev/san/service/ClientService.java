package com.dev.san.service;

import com.dev.san.model.entity.Client;
import com.dev.san.model.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientService {
    private static final String NOT_FOUND = "Client not found!";

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> listAll()  {return this.clientRepository.findAll();}

    public Client findById(UUID id) {
        Optional<Client> clientOptional = this.clientRepository.findById(id);
        if (!clientOptional.isPresent())
            throw new IllegalArgumentException(NOT_FOUND);
        return clientOptional.get();
    }

    public void delete(Client client) {
        this.clientRepository.delete(client);
    }
}
