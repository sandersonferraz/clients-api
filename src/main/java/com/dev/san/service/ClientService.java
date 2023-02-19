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

    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {return this.clientRepository.findAll();}

    public Optional<Client> findById(UUID id) {
        return this.clientRepository.findById(id);
    }

    public void delete(Client client) {
        this.clientRepository.delete(client);
    }
}
