package com.dev.san.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 11)
    private String cpf;
    @Column(name = "register_date")
    private LocalDate registerDate;

}
