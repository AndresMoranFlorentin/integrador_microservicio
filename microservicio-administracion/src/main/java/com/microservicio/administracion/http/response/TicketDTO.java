package com.microservicio.administracion.http.response;

import com.microservicio.administracion.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {
    private LocalDateTime fecha;
    private Long idCuenta;
    private Long id_usuario;
    private Double monto;

    public TicketDTO(Ticket ticket) {
        this.fecha = ticket.getFecha();
        this.idCuenta = ticket.getIdCuenta();
        this.id_usuario = ticket.getId_usuario();
        this.monto = ticket.getMonto();
    }
}
