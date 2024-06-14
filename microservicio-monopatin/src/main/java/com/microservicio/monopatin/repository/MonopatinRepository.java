package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MonopatinRepository extends JpaRepository <Monopatin, Long>{


    @Query("UPDATE Monopatin m SET m.tarifa =:tarifa")
    void setTarifa(Double tarifa);

    @Query("UPDATE Monopatin m SET m.tarifaExtra =:tarifa")
    void setTarifaExtra(Double tarifa);

    @Query("SELECT m FROM Monopatin m ORDER BY m.kmAcumulados")
    List<MonopatinDto> getReporteXkm();

    @Query("SELECT m FROM Monopatin m JOIN m.viajes v WHERE YEAR(v.fin) = :year GROUP BY m " +
            "HAVING COUNT(v) > :cantViajes ")
    List<MonopatinDto> getReporteViajes(int cantViajes, int year);

    @Query("SELECT m FROM Monopatin m WHERE m.estado = 'Disponible' ")
    List<MonopatinDto> getDisponibles();

    @Query("SELECT m.tarifa FROM Monopatin m")
    int getTarifa();
}
