package com.microservicio.mantenimiento.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monopatin {

    private Long id_monopatin;
    private int  km_monopatin;

}