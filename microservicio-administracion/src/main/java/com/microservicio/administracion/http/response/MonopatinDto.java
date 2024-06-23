package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {

    private Long id;
    private String modelo;
    private String ubicacion;
    private int kmAcumulados;

}

