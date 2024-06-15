package com.microservicio.mantenimiento.repository;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.model.MonopatinDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MantenimientoRepository")
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {

    Object update(MonopatinDto entity);

}
