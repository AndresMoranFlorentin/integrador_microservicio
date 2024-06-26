package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class CuentaDTO {
    private String nombre_cuenta;
     private Double monto;
     private boolean habilitado=true;
     private LocalDateTime fecha_de_alta;

    public CuentaDTO(String nombreCuenta, Double monto,boolean habilitado,LocalDateTime fecha) {
        this.nombre_cuenta=nombreCuenta;
        this.monto=monto;
        this.habilitado=habilitado;
        this.fecha_de_alta=fecha;

    }

    public String getNombre() {
        return this.nombre_cuenta;
    }
}
