package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "monopatin")
public class Monopatin {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idMonopatin;
    @Column
    private String modelo;
    @Column
    private int kmAcumulados;
    @Column
    private String estado;
    @Column
    private double latitud;
    @Column
    private double longitud;
    @OneToMany
    private List<Viaje> viajes;

    public double calcularDistancia(double lat, double lon) {
        final int R = 6371; // Radio de la Tierra en km
        double latDistance = Math.toRadians(lat - this.latitud);
        double lonDistance = Math.toRadians(lon - this.longitud);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(lat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // distancia en kilómetros
    }

}
