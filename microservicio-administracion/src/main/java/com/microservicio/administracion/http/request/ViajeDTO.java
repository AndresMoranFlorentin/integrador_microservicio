package com.microservicio.administracion.http.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class ViajeDTO {
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Long idMonopatin;
    private Long idCuenta;
    private Long idUsuario;
    private Double costo;
}
