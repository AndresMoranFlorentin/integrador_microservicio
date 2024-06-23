package com.microservicio.cliente.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Getter
@Data
public class UsuarioDto {
    private String nombre;
    @Column
    private Long celular;
    @Column
    private String email;
    @Column
    private String apellido;

    public UsuarioDto(String nombre,Long cel,String mail,String apellido) {
        this.nombre=nombre;
        this.email=mail;
        this.celular=cel;
        this.apellido=apellido;
    }
}
