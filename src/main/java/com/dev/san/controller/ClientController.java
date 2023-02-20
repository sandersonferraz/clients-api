package com.dev.san.controller;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import com.dev.san.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/clients/v1")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> listAll() {
        return this.clientService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto findById(@PathVariable(value = "id") UUID id) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(this.clientService.findById(id), clientDto);
        return clientDto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto save(@Valid @RequestBody ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(this.clientService.save(client), clientDto);
        return clientDto;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto update(@PathVariable("id") UUID id, @Valid @RequestBody ClientDto clientDto) {
        Client client = this.clientService.findById(id);
        client.setId(client.getId());
        BeanUtils.copyProperties(this.clientService.save(client), clientDto);
        return clientDto;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        Client client = this.clientService.findById(id);
        this.clientService.delete(client);
    }


}
