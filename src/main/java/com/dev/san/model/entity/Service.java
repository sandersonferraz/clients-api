package com.dev.san.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 255)
    private Service description;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private BigDecimal value;
}
