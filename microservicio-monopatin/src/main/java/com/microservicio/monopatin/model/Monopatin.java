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
    private String ubicacion;
    @Column
    private int kmAcumulados;
    @Column
    private String estado;
    @OneToMany
    private List<Viaje> viajes;



}
