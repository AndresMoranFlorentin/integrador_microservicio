package com.microservicio.mantenimiento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mantenimiento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long id_monopatin;

    @Column
    private LocalDateTime fecha_inicio;

    @Column
    private LocalDateTime fecha_fin;

    @Column
    private String descripcion;

    @Column
    private int km_monopatin;

    public Mantenimiento(Long id_monopatin, int km_monopatin) {
        this.id_monopatin = id_monopatin;
        this.km_monopatin = km_monopatin;
        this.fecha_inicio = LocalDateTime.now();
        this.fecha_fin = null;
        this.descripcion = null;
    }
}
