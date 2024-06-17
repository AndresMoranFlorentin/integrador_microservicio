package com.microservicio.mantenimiento.repository;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.model.MonopatinDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

    @Query("SELECT m FROM Mantenimiento m WHERE m.id_monopatin = :idm")
    public Mantenimiento getMantenimientoById(@Param("idm") Long idm);
}
