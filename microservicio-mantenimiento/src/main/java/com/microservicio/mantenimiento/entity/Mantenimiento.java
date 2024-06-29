package com.microservicio.mantenimiento.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
@Document(collection = "mantenimientos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento{
    @Id
     private ObjectId id;

     private Long id_monopatin;

     private LocalDateTime fecha_inicio;

     private LocalDateTime fecha_fin;

     private String descripcion;

     private int km_monopatin;

    public Mantenimiento(Long id_monopatin, int km_monopatin) {
        this.id_monopatin = id_monopatin;
        this.km_monopatin = km_monopatin;
        this.fecha_inicio = LocalDateTime.now();
        this.fecha_fin = null;
        this.descripcion = null;
    }

    public Mantenimiento(Long idMonopatin, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion, int kmMonopatin) {
        this.id_monopatin = idMonopatin;
        this.fecha_inicio = fechaInicio;
        this.fecha_fin = fechaFin;
        this.descripcion = descripcion;
        this.km_monopatin = kmMonopatin;

    }
}
