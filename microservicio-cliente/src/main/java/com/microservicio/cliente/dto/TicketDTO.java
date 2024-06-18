package com.microservicio.cliente.dto;

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
}
