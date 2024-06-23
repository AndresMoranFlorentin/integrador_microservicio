package com.microservicio.mantenimiento.service;

import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.feignClient.MonopatinFeignClient;
import com.microservicio.mantenimiento.model.MantenimientoMonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinDtoConPausa;
import com.microservicio.mantenimiento.model.MonopatinEnMantenimientoDTO;
import com.microservicio.mantenimiento.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<?> getMonopatinesPorKm(@PathVariable boolean pausa) throws Exception {
        List<?> monopatines=new ArrayList();
        if(pausa==true){
           return  monopatines=monopatinClient.getMonopatinesPorKmConPausa();
        }
        else{
           return  monopatines = monopatinClient.getMonopatinesPorKm();
        }
    }

    public void guardar(MonopatinEnMantenimientoDTO mEMDTO) {
        Mantenimiento m = new Mantenimiento(mEMDTO.getId_monopatin(), mEMDTO.getFecha_inicio(), mEMDTO.getFecha_fin(), mEMDTO.getDescripcion(), mEMDTO.getKm_monopatin());
        mantRepo.save(m);
     }
}
