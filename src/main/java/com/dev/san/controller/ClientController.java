package com.dev.san.controller;

import com.dev.san.dto.ClientDto;
import com.dev.san.model.entity.Client;
import com.dev.san.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/clients/v1")
@Slf4j
public class ClientController {
    private final ClientService clientService;


    @GetMapping("/")
    public ResponseEntity<Page<ClientDto>> listAll(Pageable pageable) {
        Page<ClientDto> clients = this.clientService.listAll(pageable);
        log.info(clients.toString());
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable(value = "id") Integer id) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(this.clientService.findById(id), clientDto);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/{cpf}/cpf")
    public ResponseEntity<ClientDto> findByCpf(@PathVariable(value = "cpf") String cpf) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(this.clientService.findByCpf(cpf), clientDto);
        return ResponseEntity.ok(clientDto);
    }

    @PostMapping("/")
    public ResponseEntity<ClientDto> save(@Valid @RequestBody ClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(clientDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable("id") Integer id, @Valid @RequestBody ClientDto clientDto) {
        ClientDto clientUpdate = this.clientService.findById(id);
        clientUpdate.setCpf(clientDto.getCpf());
        clientUpdate.setFullName(clientDto.getFullName());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(clientUpdate));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        ClientDto client = this.clientService.findById(id);
        this.clientService.delete(client);
    }


}
