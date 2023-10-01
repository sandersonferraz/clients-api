package com.dev.san.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientPutDto {

    private Integer id;
    @NotBlank
    @Size(max = 150)
    private String fullName;
    @NotBlank
    @Size(max = 11)
    private String cpf;
    private LocalDate serviceDate;

}
