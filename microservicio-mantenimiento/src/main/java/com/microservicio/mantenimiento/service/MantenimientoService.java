package com.microservicio.mantenimiento.service;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.feignClient.MonopatinFeignClient;
import com.microservicio.mantenimiento.model.MonopatinDto;
import com.microservicio.mantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("MantenimientoService")
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantRepo;

    @Autowired
    private MonopatinFeignClient monopatinClient;

    @Transactional
    public Mantenimiento save(MonopatinDto entity) throws Exception {
        try {
            Mantenimiento mantEntity = new Mantenimiento(entity.getId(), entity.getKmAcumulados());
            return mantRepo.save(mantEntity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Object update(MonopatinDto entity) throws Exception {
        try {
            return mantRepo.update(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MonopatinDto> getMonopatinesPorKm() throws Exception {
        List<MonopatinDto> monopatines = monopatinClient.getMonopatinesPorKm();
        return monopatines;
    }
}
