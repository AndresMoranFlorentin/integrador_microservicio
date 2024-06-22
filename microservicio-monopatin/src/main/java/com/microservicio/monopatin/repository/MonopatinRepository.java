package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MonopatinRepository extends JpaRepository <Monopatin, Long>{
    @Query("SELECT m FROM Monopatin m ORDER BY m.kmAcumulados DESC")
    List<MonopatinDto> getReporteXkm();

    @Query("SELECT m FROM Monopatin m JOIN Viaje v ON m.idMonopatin = v.idMonopatin AND YEAR(v.fin) = :year GROUP BY m " +
            "HAVING COUNT(v) > :cantViajes ")
    List<MonopatinDto> getReporteViajes(int cantViajes, int year);

    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado = 'Disponible' ")
    Integer getDisponibles();
    @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado != 'Disponible'")
    Integer getNoDisponibles();
}
