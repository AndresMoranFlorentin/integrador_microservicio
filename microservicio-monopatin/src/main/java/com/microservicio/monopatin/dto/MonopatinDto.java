package com.microservicio.monopatin.dto;

import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.model.Viaje;
import lombok.Getter;


import java.util.List;

@Getter
public class MonopatinDto {

    private Long id;
    private String ubicacion;
    private int kmAcumulados;
    private double tarifa;
    private double tarifaExtra;
    private List<Viaje> viajes;

    public MonopatinDto (Monopatin m){
        this.id = m.getIdMonopatin();
        this.ubicacion = m.getUbicacion();
        this.kmAcumulados = m.getKmAcumulados();
        this.tarifa = m.getTarifa();
        this.tarifaExtra = m.getTarifaExtra();
        this.viajes.clear();
        this.viajes.addAll(m.getViajes());
    }

}


