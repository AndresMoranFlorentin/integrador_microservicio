package com.microservicio.mantenimiento.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonopatinDtoConPausa {
    private Long id;
    private String modelo;
    private int kmAcumulados;
    private double tiempo_pausado;
    public MonopatinDtoConPausa(Long idMonopatin, String modelo, int kmAcumulados, double tiempoPausado) {
        this.id = idMonopatin;
        this.modelo = modelo;
        this.kmAcumulados = kmAcumulados;
        this.tiempo_pausado = tiempoPausado;
    }

}
