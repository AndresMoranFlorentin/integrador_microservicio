package com.microservicio.monopatin.dto;

import com.microservicio.monopatin.model.Viaje;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViajeDto {
    private Long idViaje;
    private LocalDateTime inicio;

    private LocalDateTime fin;

    private Long idMonopatin;

    private Long idCuenta;

    private Long idUsuario;

    private Double costo;

    private Boolean conPausa;

    private LocalDateTime inicioPausa;

    private LocalDateTime finPausa;

    private Double tiempoPausado;

    public ViajeDto(Viaje viaje){
        this.idViaje=viaje.getIdViaje();
        this.inicio = viaje.getInicio();
        this.fin = viaje.getFin();
        this.costo = viaje.getCosto();
        this.idCuenta = viaje.getIdCuenta();
        this.idUsuario = viaje.getIdUsuario();
        this.idMonopatin = viaje.getIdMonopatin();
        this.inicioPausa = viaje.getInicioPausa();
        this.finPausa = viaje.getFinPausa();
        this.tiempoPausado = viaje.getTiempoPausado();
        this.conPausa = viaje.getConPausa();
    }


}
