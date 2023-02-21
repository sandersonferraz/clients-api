package com.dev.san.dto;

import com.dev.san.model.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    private Integer id;
    @NotBlank
    @Size(max = 150)
    private String fullName;
    @NotBlank
    @Size(max = 11)
    private String cpf;
    private LocalDate serviceDate;


    public ClientDto convert(Client client) {
        BeanUtils.copyProperties(client, this);
        return this;
    }

    public List<ClientDto> convertList(List<Client> clients) {
        ClientDto clientDto = new ClientDto();
        List<ClientDto> clientDtoList = new ArrayList<>();
        clients.forEach(client -> clientDtoList.add(clientDto.convert(client)));
        return clientDtoList;
    }

    public Page<ClientDto> convertPage(Page<Client> clientPage) {
        ClientDto clientDto = new ClientDto();
        Page<ClientDto> clientDtoPage = new PageImpl<>(new ArrayList<>());
        clientPage.forEach(client -> clientDtoPage.toList().add(clientDto.convert(client)));
        return clientDtoPage;

    }


}
