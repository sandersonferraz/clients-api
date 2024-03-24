package com.dev.san.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "services_provides")
public class ServicesProvided implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "service_description", nullable = false, length = 255)
    @NotEmpty(message = "The full name cannot be empty.")
    private String serviceDescription;
    @ManyToOne
    @NotEmpty(message = "The Client cannot be empty..")
    private Client client;
    @Column(name = "service_value")
    @NotEmpty(message = "The value cannot be empty.")
    private BigDecimal serviceValue;
}
