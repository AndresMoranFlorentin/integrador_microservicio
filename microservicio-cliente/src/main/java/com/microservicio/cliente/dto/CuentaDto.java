package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
public class CuentaDto {
    @Column
    private String nombre;
    @Column
    private Float monto;
    @Column
    private LocalDateTime fecha_de_alta;
}
