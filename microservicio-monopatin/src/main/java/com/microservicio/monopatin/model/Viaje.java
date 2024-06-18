package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@Table(name = "viaje")
public class Viaje implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="idViaje")
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
    @ManyToOne
    private Tarifa tarifa;
    @Column
    private Double tarifaExtra;

    public Viaje(Long idMonopatin, Long idCuenta, Long idUsuario,Tarifa tarifa){
        this.idMonopatin = idMonopatin;
        this.idCuenta = idCuenta;
        this.idUsuario = idUsuario;
        this.inicio = LocalDateTime.now();
        this.tarifa=tarifa;
        this.tarifaExtra=0.10;
    }

    public void setFin() {
        this.fin = LocalDateTime.now();
    }

    public void setCosto(){
        Duration tiempo = Duration.between(this.inicio, this.fin);
        long horas = tiempo.toHours();
        long minutos = tiempo.toMinutesPart();
        long segundos = tiempo.toSecondsPart();

        Long tiempoHoras = horas + (minutos/60) + (segundos/3600);

        Double tiempoH = tiempoHoras.doubleValue();

        this.costo = tiempoH * this.tarifa.getPrecio();
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
