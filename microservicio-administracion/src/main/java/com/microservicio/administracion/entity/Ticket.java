package com.microservicio.administracion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;
    @Column
    private Long id_usuario;
    @Column
    private double monto;
    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Administrador admin;
}
