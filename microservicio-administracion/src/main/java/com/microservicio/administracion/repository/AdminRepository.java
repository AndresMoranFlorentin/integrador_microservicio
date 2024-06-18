package com.microservicio.administracion.repository;

import com.microservicio.administracion.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AdminRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT SUM(t.monto) FROM Ticket t WHERE t.fecha BETWEEN :mes1 AND :mes2")
    Double getTotalFacturado(@Param("mes1") LocalDateTime mes1, @Param("mes2") LocalDateTime mes2);
}
