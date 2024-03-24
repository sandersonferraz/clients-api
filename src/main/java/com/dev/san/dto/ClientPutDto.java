package com.dev.san.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPutDto {
    private Integer id;
    @NotBlank(message = "{fullName.is.required}")
    @Size(max = 150, message = "{fullName.max.characters}")
    private String fullName;
    @NotBlank(message = "{cpf.is.required}")
    @CPF(message = "{cpf.is.invalid}")
    @Size(max = 14, message = "{cpf.max.characters}")
    private String cpf;
    private LocalDate serviceDate;

}
