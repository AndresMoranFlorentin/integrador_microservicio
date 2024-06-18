package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReporteTotalFacturado {
    private LocalDateTime mesInicio;
    private LocalDateTime  mesFin;
    private Double totalFacturado;
}
