package com.dev.san.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "services_provides")
public class ServicesProvided implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "service_description", nullable = false, length = 255)
    private String serviceDescription;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @Column(name = "service_value")
    private BigDecimal serviceValue;
}
