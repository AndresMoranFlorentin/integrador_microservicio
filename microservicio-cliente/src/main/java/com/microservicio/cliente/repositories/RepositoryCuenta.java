package com.microservicio.cliente.repositories;

import com.microservicio.cliente.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCuenta extends JpaRepository<Cuenta,Long> {
}
