package com.microservicio.monopatin.dto;

import com.microservicio.monopatin.model.Viaje;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
public class ViajeDto {

    private LocalDateTime inicio;

    private LocalDateTime fin;

    private Long idMonopatin;

    private Long idCuenta;

    private Long idUsuario;

    private Long costo;

    public ViajeDto(Viaje viaje){
        this.inicio = viaje.getInicio();
        this.fin = viaje.getFin();
        this.costo = viaje.getCosto();
        this.idCuenta = viaje.getIdCuenta();
        this.idUsuario = viaje.getIdUsuario();
        this.idMonopatin = viaje.getIdMonopatin();
    }


}
