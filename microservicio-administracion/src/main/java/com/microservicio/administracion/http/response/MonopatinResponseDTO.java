package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonopatinResponseDTO {
    private String ubicacion;
    private int kmAcumulados;
    private String estado;
    private int viajes;
}
