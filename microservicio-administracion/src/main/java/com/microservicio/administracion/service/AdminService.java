package com.microservicio.administracion.service;

import com.microservicio.administracion.client.MonopatinClient;
import com.microservicio.administracion.entity.Administrador;
import com.microservicio.administracion.http.response.MonopatinDTO;
import com.microservicio.administracion.http.response.MonopatinconXViajesResponseDTO;
import com.microservicio.administracion.http.response.ReporteMonopatinesDTO;
import com.microservicio.administracion.repository.AdminRepository;
import com.microservicio.administracion.http.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import com.microservicio.administracion.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MonopatinClient monopatinClient;

    @Transactional
    public ReporteMonopatinesDTO getReporteMonopatines(){
        ReporteMonopatinesDTO rmDTO = monopatinClient.getReporteMonopatines();
        return rmDTO;
    }

    @Transactional
    public MonopatinconXViajesResponseDTO getMonopatinesConMasViajes(int año, int minViajes){
        List<MonopatinDTO> monopatines = monopatinClient.getMonopatinesConMasViajes(año, minViajes);
        return MonopatinconXViajesResponseDTO.builder()
                .monopatines(monopatines)
                .build();
    }

    @Transactional
    public AdministradorResponseDTO getAdministrador(Long id_admin) throws NotFoundException {
        Administrador admin = adminRepository.findById(id_admin).get();
        return new AdministradorResponseDTO(admin.getNombre());
    }

    @Transactional
    public AdministradorResponseDTO save(AdministradorRequestDTO request) {
        Administrador admin = new Administrador();
        admin.setNombre(request.getNombre());
        adminRepository.save(admin);
        return new AdministradorResponseDTO(admin.getNombre());
    }

    @Transactional
    public AdministradorResponseDTO update(Long id, AdministradorRequestDTO entity) throws Exception {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Administrador", id));
        admin.setNombre(entity.getNombre());
        adminRepository.save(admin);
        return new AdministradorResponseDTO(admin.getNombre());
    }

    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            adminRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Manejar cualquier excepción y registrarla
            e.printStackTrace(); // Registra la excepción en la consola o en los logs
            return false;
        }
    }
}
