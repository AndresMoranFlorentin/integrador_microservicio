package com.microservicio.monopatin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservicio.monopatin.model.Monopatin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonIgnoreProperties( ignoreUnknown = true )
public class MonopatinDtoNuevo {

    private String modelo;

}
