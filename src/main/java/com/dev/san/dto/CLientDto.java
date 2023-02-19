package com.dev.san.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CLientDto {

    private UUID id;
    @NotBlank
    @Size(max = 150)
    private String fullName;
    @NotBlank
    @Size(max = 11)
    @UniqueElements
    private String cpf;
    private LocalDate serviceDate;

}
