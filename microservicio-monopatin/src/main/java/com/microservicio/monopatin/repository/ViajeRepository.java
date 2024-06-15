package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository <Viaje, Long> {

}
