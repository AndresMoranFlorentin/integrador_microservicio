package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
@AllArgsConstructor
public class CuentaDto {
    @Column
    private String nombre;
    @Column
    private Float monto;
    @Column
    private LocalDate fecha_de_alta;
}
