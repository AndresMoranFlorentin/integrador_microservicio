package com.microservicio.mantenimiento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {
    private Long id;
    private String modelo;
    private String ubicacion;
    private int kmAcumulados;

}


