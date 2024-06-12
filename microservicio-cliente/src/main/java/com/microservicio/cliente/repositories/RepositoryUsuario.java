package com.microservicio.cliente.repositories;

import com.microservicio.cliente.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<Usuario,Long> {

}
