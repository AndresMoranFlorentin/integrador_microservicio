package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ViajeDto {
    @Column
    private Long id_monopatin;
    @Column
    private Long id_usuario;
    @Column
    private Long id_cuenta;
    @Column
    private Date inicio;
    @Column
    private Date fin_viaje;
    public ViajeDto(Long idM, Long idU, Long idC, Date ini, Date fin){
        this.id_cuenta=idC;
        this.id_monopatin=idM;
        this.id_usuario=idU;
        this.inicio=ini;
        this.fin_viaje=fin;
    }
}
