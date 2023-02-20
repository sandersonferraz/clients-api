package com.dev.san.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "full_name", nullable = false, length = 150)
    @NotBlank
    private String fullName;
    @Column(nullable = false, unique = true, length = 11)
    @NotBlank(message = "Mandatory CPF. Information not fund.")
    private String cpf;
    @Column(name = "service_date")
    private LocalDate serviceDate;

    @PrePersist
    private void prePersist(){
        setServiceDate(LocalDate.now());
    }

}
