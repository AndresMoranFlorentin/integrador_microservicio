package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idViaje;

    @Column
    private LocalDateTime inicio;
    @Column
    private LocalDateTime fin;
    @Column
    private Long idMonopatin;
    @Column
    private Long idCuenta;
    @Column
    private Long idUsuario;
    @Column
    private Double costo;
    @Column
    private Boolean conPausa = false;
    @Column
    private LocalDateTime inicioPausa;
    @Column
    private LocalDateTime finPausa;
    @Column
    private Double tiempoPausado;

    public Viaje(Long idMonopatin, Long idCuenta, Long idUsuario){
        this.idMonopatin = idMonopatin;
        this.idCuenta = idCuenta;
        this.idUsuario = idUsuario;
        this.inicio = LocalDateTime.now();
    }

    public void setFin() {
        this.fin = LocalDateTime.now();
    }

    public void setCosto(int tarifa){
        Duration tiempo = Duration.between(this.inicio, this.fin);
        long horas = tiempo.toHours();
        long minutos = tiempo.toMinutesPart();
        long segundos = tiempo.toSecondsPart();

        Long tiempoHoras = horas + (minutos/60) + (segundos/3600);

        Double tiempoH = tiempoHoras.doubleValue();

        this.costo = tiempoH * tarifa;
    }

    public void setInicioPausa(){
        this.inicioPausa = LocalDateTime.now();
        this.conPausa = true;
    }

    public void setFinPausa(){
        this.finPausa = LocalDateTime.now();
    }

    public void setTiempoPausado(){
        Duration tiempo = Duration.between(this.inicio, this.fin);
        long horas = tiempo.toHours();
        long minutos = tiempo.toMinutesPart();
        long segundos = tiempo.toSecondsPart();
        Long tiempoHoras = horas + (minutos/60) + (segundos/3600);
        this.tiempoPausado = tiempoHoras.doubleValue();
    }
}
