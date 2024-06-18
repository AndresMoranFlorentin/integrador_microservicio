package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TarifaRepository extends JpaRepository<Tarifa,Long> {
}
