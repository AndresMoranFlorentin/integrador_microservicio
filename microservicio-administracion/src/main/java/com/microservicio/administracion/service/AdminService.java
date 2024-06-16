package com.microservicio.administracion.service;

import com.microservicio.administracion.client.ClienteClient;
import com.microservicio.administracion.client.MonopatinClient;
import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.response.CuentaResponseDTO;
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

    @Autowired
    private ClienteClient clienteClient;


    @Transactional
    public CuentaResponseDTO inhabilitarCuenta(Long id_cuenta) {
        return clienteClient.inhabilitarCuenta(id_cuenta);
    }

    @Transactional
    public CuentaResponseDTO habilitarCuenta(Long id_cuenta) {
        CuentaResponseDTO cDTO = clienteClient.habilitarCuenta(id_cuenta);
        return clienteClient.habilitarCuenta(id_cuenta);
    }

    @Transactional
    public ReporteMonopatinesDTO getReporteMonopatines(){
        ReporteMonopatinesDTO rmDTO = monopatinClient.getReporteMonopatines();
        return rmDTO;
    }


    @Transactional
    public List<MonopatinDTO> getMonopatinesCercanos(String ubicacion) {
        List<MonopatinDTO> monopatinesCercanos = monopatinClient.getMonopatinesCercanos(ubicacion);
        return monopatinesCercanos;
    }

    @Transactional
    public MonopatinconXViajesResponseDTO getMonopatinesConMasViajes(int anio, int minViajes){
        List<MonopatinDTO> monopatines = monopatinClient.getMonopatinesConMasViajes(anio, minViajes);
        return MonopatinconXViajesResponseDTO.builder()
                .monopatines(monopatines)
                .build();
    }

    public CuentaResponseDTO agregarCuenta(CuentaDTO cuenta) {
        CuentaResponseDTO cuentaDTO = clienteClient.agregarCuenta(cuenta);
        if(cuentaDTO != null){
            return cuentaDTO;
        }else {
            return null;
        }
    }

    public String setearTarifas(Double tarifa) {
        String mensaje = monopatinClient.setearTarifas(tarifa);
        return mensaje;
    }
}
