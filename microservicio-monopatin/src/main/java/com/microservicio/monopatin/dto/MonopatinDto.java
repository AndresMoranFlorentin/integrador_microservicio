package com.microservicio.monopatin.dto;

import com.microservicio.monopatin.model.Monopatin;
import lombok.Getter;



@Getter
public class MonopatinDto {

    private Long id;
    private String modelo;
    private String ubicacion;
    private int kmAcumulados;

    public MonopatinDto (Monopatin m){
        this.id = m.getIdMonopatin();
        this.modelo = m.getModelo();
        this.ubicacion = "latitud: "+ m.getLatitud()+", longitud: "+ m.getLongitud();
        this.kmAcumulados = m.getKmAcumulados();
    }

}

