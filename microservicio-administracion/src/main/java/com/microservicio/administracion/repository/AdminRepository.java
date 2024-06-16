package com.microservicio.administracion.repository;

import com.microservicio.administracion.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Ticket, Long> {

}
