package com.microservicio.cliente.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Monopatin {
    @Column
    private String ubicacion;
    @Column
    private int kmAcumulados;
    @Column
    private String estado;
    @Column
    private double tarifa;
}
