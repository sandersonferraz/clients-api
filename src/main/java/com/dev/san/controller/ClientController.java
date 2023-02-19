package com.dev.san.controller;

import com.dev.san.dto.CLientDto;
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
    private static final String NOT_FOUND = "Client not found!";
    private final ClientService clientService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findAll() {
        return this.clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClientById(@PathVariable(value = "id") UUID id) {
        Optional<Client> clientOptional = this.clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@Valid @RequestBody CLientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(client));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") UUID id, @Valid @RequestBody CLientDto cLientDto) {
        Client client = new Client();
        Optional<Client> clientOptional = this.clientService.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }
        BeanUtils.copyProperties(clientOptional, client);
        client.setId(clientOptional.get().getId());
        client.setServiceDate(clientOptional.get().getServiceDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(client));
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        Optional<Client> clientOptional = this.clientService.findById(id);
        if (!clientOptional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
        }else 
         {
            this.clientService.delete(clientOptional.get());
        }
    }


}
