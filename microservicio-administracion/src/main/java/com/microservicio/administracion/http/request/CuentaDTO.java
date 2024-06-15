package com.microservicio.administracion.http.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class CuentaDTO {
    private Long id_cuenta;
    private String nombre_cuenta;
    private Float monto;
    private boolean habilitado=true;
    private LocalDateTime fecha_de_alta;
}
