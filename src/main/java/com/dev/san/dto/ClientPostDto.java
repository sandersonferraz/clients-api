package com.dev.san.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPostDto {
    @NotBlank(message = "{field.is.required}")
    @Size(max = 150, message = "{field.max.150}")
    private String fullName;
    @NotBlank(message = "{field.is.required}")
    @CPF(message = "{field.is.invalid}")
    @Size(max = 14, message = "{field.max.11}")
    private String cpf;

}
