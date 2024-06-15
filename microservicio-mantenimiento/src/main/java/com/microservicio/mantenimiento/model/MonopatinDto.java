package com.microservicio.mantenimiento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {

    private Long id;
    private String ubicacion;
    private int kmAcumulados;
    private double tarifa;
    private double tarifaExtra;

}