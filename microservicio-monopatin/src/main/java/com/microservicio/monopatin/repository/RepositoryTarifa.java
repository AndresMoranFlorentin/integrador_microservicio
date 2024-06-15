package com.microservicio.monopatin.repository;

import com.microservicio.monopatin.model.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTarifa extends JpaRepository<Long, Tarifa> {
}
