package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonopatinDTO {
    private Long idMonopatin;
    private String ubicacion;
    private int kmAcumulados;
    private String estado;
    private double tarifa;
    private double tarifaExtra;
    private int cantViajes;
}
