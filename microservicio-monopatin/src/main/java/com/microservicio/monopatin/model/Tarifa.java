package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tarifa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarifa;
    @Column
    private Double precio;
    @ManyToOne
    @JoinColumn(name="idViaje")
    private Viaje viaje;
    public Tarifa( Double precio){
        this.precio=precio;
    }
    public void addViaje(Viaje v){
        this.viaje=v;
    }
}
