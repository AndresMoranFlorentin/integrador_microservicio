package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
public class CuentaDTO {
    @Column
    private Long id_cuenta;
    @Column
    private String nombre_cuenta;
    @Column
    private Float monto;
    @Column
    private boolean habilitado;
    @Column
    private LocalDateTime fecha_de_alta;

    public CuentaDTO(Long id,String nombreCuenta, Float monto,boolean habilitado,LocalDateTime fechaDeAlta) {
        this.id_cuenta=id;
        this.nombre_cuenta=nombreCuenta;
        this.monto=monto;
        this.habilitado=habilitado;
        this.fecha_de_alta=fechaDeAlta;

    }

    public String getNombre() {
        return this.nombre_cuenta;
    }
}
