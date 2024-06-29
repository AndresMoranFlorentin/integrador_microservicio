package com.microservicio.mantenimiento.repository;

import com.microservicio.mantenimiento.entity.Mantenimiento;
 import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends MongoRepository<Mantenimiento, Long> {

     Mantenimiento getMantenimientoById(@Param("idm") Long idm);
}
