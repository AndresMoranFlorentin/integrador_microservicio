package com.microservicio.monopatin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class ViajeInicioDto {

    private Long idMonopatin;
    private Long idCuenta;
    private Long idUsuario;
}
