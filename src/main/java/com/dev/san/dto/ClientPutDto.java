package com.dev.san.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPutDto {

    @NotBlank
    @Size(max = 150)
    private String fullName;
    @NotBlank
    @Size(max = 14)
    private String cpf;

}
