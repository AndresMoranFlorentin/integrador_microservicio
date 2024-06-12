package com.microservicio.monopatin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idViaje;

    @Column
    private Date inicio;
    @Column
    private Date fin;
    @Column
    private Long idMonopatin;
    @Column
    private Long idCuenta;


}
