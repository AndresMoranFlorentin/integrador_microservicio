package com.microservicio.administracion.service.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdministradorResponseDTO {
    private String nombre;

    public AdministradorResponseDTO(String nombre) {
        this.nombre = nombre;
    }
}
