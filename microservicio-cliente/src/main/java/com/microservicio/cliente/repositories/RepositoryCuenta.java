package com.microservicio.cliente.repositories;

import com.microservicio.cliente.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCuenta extends JpaRepository<Cuenta,Long> {
}
