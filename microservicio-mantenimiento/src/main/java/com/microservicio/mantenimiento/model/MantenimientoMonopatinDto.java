package com.microservicio.mantenimiento.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoMonopatinDto {

    private Long id;
    private int kmAcumulados;

    public MantenimientoMonopatinDto(MonopatinDto mDto) {
        this.id = mDto.getId();
    }

}