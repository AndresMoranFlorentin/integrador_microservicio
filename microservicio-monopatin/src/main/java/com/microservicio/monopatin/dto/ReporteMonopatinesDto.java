package com.microservicio.monopatin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReporteMonopatinesDto {
    private int enMantenimiento;
    private int operativos;
}
