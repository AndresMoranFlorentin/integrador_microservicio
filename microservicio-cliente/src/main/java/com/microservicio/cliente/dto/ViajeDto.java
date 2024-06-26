package com.microservicio.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViajeDto {
    private Long idViaje;
    private LocalDateTime inicio;

    private LocalDateTime fin;

    private Long idMonopatin;

    private Long idCuenta;

    private Long idUsuario;

    private Double costo;

    private Boolean conPausa;

    private LocalDateTime inicioPausa;

    private LocalDateTime finPausa;

    private Double tiempoPausado;


}
