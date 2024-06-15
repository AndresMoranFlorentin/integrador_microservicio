package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tarifa;
    @Column
    private Double tarifa;
    @OneToOne
    private Viaje viaje;
}
