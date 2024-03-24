package com.dev.san.dto;

import com.dev.san.model.entity.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicesProvidedPutDto {

    private Integer id;
    @NotBlank
    @Size(max = 255)
    private String serviceDescription;
    @NotBlank
    private Client client;
    @NotBlank
    private BigDecimal serviceValue;

}
