package com.microservicio.administracion.entity;

import com.microservicio.administracion.http.request.ViajeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime fecha;
    @Column
    private Long idCuenta;
    @Column
    private Long id_usuario;
    @Column
    private Double monto;

    public Ticket(ViajeDTO viaje){
        this.fecha = LocalDateTime.now();
        this.idCuenta = viaje.getIdCuenta();
        this.id_usuario = viaje.getIdUsuario();
        this.monto = viaje.getCosto();
    }
}
