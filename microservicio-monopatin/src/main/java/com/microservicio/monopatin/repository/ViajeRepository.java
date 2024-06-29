package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository <Viaje, Long> {
    @Query("SELECT v.idMonopatin" +
            " FROM Viaje v WHERE v.conPausa = true ")
    public List<Long> getIdMonopatinConPausa();
}
