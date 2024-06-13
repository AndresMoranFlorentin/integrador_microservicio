package com.microservicio.mantenimiento.service;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.model.Monopatin;
import com.microservicio.mantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("MantenimientoService")
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantRepo;

    @Transactional
    public Mantenimiento save(Monopatin entity) throws Exception {
        try {
            Mantenimiento mantEntity = new Mantenimiento(entity.getId_monopatin(), entity.getKm_monopatin());
            return mantRepo.save(mantEntity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
