package com.dev.san.controller;

import com.dev.san.dto.ClientPostDto;
import com.dev.san.dto.ClientPutDto;
import com.dev.san.model.entity.Client;
import com.dev.san.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/clients/v1")
@CrossOrigin("http://localhost:4200")
public class ClientController {
    private final ClientService clientService;


    @GetMapping()
    public ResponseEntity<Page<Client>> listAll(Pageable pageable) {
        Page<Client> clients = this.clientService.listAll(pageable);
        log.info(clients.toString());
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(this.clientService.findById(id));
    }

    @GetMapping("/{cpf}/cpf")
    public ResponseEntity<ClientPostDto> findByCpf(@PathVariable(value = "cpf") String cpf) {
        ClientPostDto clientPostDto = new ClientPostDto();
        BeanUtils.copyProperties(this.clientService.findByCpf(cpf), clientPostDto);
        return ResponseEntity.ok(clientPostDto);
    }

    @PostMapping()
    public ResponseEntity<Client> save(@Valid @RequestBody ClientPostDto clientPostDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.save(clientPostDto));
    }


    @PutMapping()
    public ResponseEntity<Client> replace(@Valid @RequestBody ClientPutDto clientPutDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body( this.clientService.replace(clientPutDto));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        this.clientService.delete(id);
    }


}
