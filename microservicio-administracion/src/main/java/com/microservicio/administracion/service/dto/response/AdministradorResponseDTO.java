package com.microservicio.administracion.service.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdministradorResponseDTO {
    private String nombre;

    public AdministradorResponseDTO(String nombre) {
        this.nombre = nombre;
    }
}
