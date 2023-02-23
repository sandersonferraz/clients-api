package com.dev.san.model.entity;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "services_provides")
@Builder
public class ServicesProvided implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "service_description", nullable = false, length = 255)
    @NotEmpty(message = "The full name cannot be empty.")
    private String serviceDescription;
    @ManyToOne
    @JoinColumn(name = "id_client")
    @NotEmpty(message = "The Client cannot be empty..")
    private Client client;
    @Column(name = "service_value")
    @NotEmpty(message = "The value cannot be empty.")
    private BigDecimal serviceValue;
}
