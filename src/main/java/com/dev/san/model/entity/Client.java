package com.dev.san.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clients")
public class Client implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false, length = 150)
    @NotBlank(message = "{field.is.required}")
    private String fullName;

    @Column(nullable = false, unique = true, length = 14)
    @NotBlank(message = "{field.is.required}")
    @CPF(message = "{field.cpf.invalid}")
    private String cpf;

    @Column(name = "service_date")
        private LocalDate serviceDate;

    @PrePersist
    private void prePersist(){
        setServiceDate(LocalDate.now());
    }

}
