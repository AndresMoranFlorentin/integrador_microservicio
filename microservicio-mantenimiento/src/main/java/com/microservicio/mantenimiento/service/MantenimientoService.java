package com.microservicio.mantenimiento.service;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.feignClient.MonopatinFeignClient;
import com.microservicio.mantenimiento.model.MantenimientoMonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinEnMantenimientoDTO;
import com.microservicio.mantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("MantenimientoService")
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantRepo;

    @Autowired
    private MonopatinFeignClient monopatinClient;

    @Transactional
    public Mantenimiento save(MantenimientoMonopatinDto entity) throws Exception {
        try {
            Mantenimiento mantEntity = new Mantenimiento(entity.getId(), entity.getKmAcumulados());
            return mantRepo.save(mantEntity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Mantenimiento guardar(MonopatinEnMantenimientoDTO entity) throws Exception {
        Mantenimiento m = new Mantenimiento(entity.getId_monopatin(), entity.getFecha_inicio(), entity.getFecha_fin(), entity.getDescripcion(), entity.getKm_monopatin());
        return mantRepo.save(m);
    }

    @Transactional
    public Mantenimiento setDispo(Long id_monopatin) throws Exception {//SACAR MONOPATIN DE MANTENIMIENTO
        try {
            monopatinClient.setDispo(id_monopatin);
            Mantenimiento m = mantRepo.getMantenimientoById(id_monopatin);
            m.setFecha_fin(LocalDateTime.now());
            return mantRepo.save(m);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @Transactional
     *     public EstudianteResponseDTO update(Long id, EstudianteRequestDTO entity) throws Exception {
     *         Estudiante estudiante = repoEstudiante.findById(id)
     *                 .orElseThrow(() -> new NotFoundException("Estudiante", id));
     *
     *         estudiante.setNombre(entity.getNombre());
     *         estudiante.setApellido(entity.getApellido());
     *         estudiante.setGenero(entity.getGenero());
     *         estudiante.setEdad(entity.getEdad());
     *         estudiante.setCiudad(entity.getCiudad());
     *         estudiante.setNum_Libreta(entity.getNum_Libreta());
     *
     *         repoEstudiante.save(estudiante);
     *         return new EstudianteResponseDTO(estudiante);
     */

    @Transactional
    public List<MonopatinDto> getMonopatinesPorKm() throws Exception {
        List<MonopatinDto> monopatines = monopatinClient.getMonopatinesPorKm();
        return monopatines;
    }
}
