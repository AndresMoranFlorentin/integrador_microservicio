package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaResponseDTO {

    private Long id_cuenta;
    private String nombre_cuenta;
    private Float monto;
    private boolean habilitado;
    private LocalDateTime fecha_de_alta;
}