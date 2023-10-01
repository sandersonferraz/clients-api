package com.dev.san.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "{field.is.required }")
    private String serviceDescription;
    @ManyToOne
    @NotEmpty(message = "{field.is.required }")
    private Client client;
    @Column(name = "service_value")
    @NotEmpty(message = "{field.is.required }")
    private BigDecimal serviceValue;
}
