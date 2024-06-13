package com.microservicio.administracion.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReporteMonopatinesDTO {
    private int enMantenimiento;
    private int operativos;
}
