package com.microservicio.administracion.http.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class MonopatinEnMantenimientoDTO {

    private Long id_monopatin;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private String descripcion;
    private int km_monopatin;
}
