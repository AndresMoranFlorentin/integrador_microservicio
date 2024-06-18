package com.microservicio.administracion.service;

import com.microservicio.administracion.client.ClienteClient;
import com.microservicio.administracion.client.MonopatinClient;
import com.microservicio.administracion.entity.Ticket;
import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.request.ViajeDTO;
import com.microservicio.administracion.http.response.*;
import com.microservicio.administracion.repository.AdminRepository;
import com.microservicio.administracion.http.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import com.microservicio.administracion.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public void mantenerMonopatin(Long id_monopatin) {
        monopatinClient.mantener(id_monopatin);
    }

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
        ReporteMonopatinesDTO rmDTO = monopatinClient.getDisponibles();
        return rmDTO;
    }


    @Transactional
    public List<MonopatinDTO> getMonopatinesCercanos(String ubicacion) {
        List<MonopatinDTO> monopatinesCercanos = monopatinClient.getMonopatinesCercanos(ubicacion);
        return monopatinesCercanos;
    }

    @Transactional
    public MonopatinconXViajesResponseDTO getMonopatinesConMasViajes(int minViajes, int anio ){
        List<MonopatinDTO> monopatines = monopatinClient.getMonopatinesConMasViajes(minViajes, anio);
        return MonopatinconXViajesResponseDTO.builder()
                .monopatines(monopatines)
                .build();
    }

    @Transactional
    public CuentaResponseDTO agregarCuenta(CuentaDTO cuenta) {
        CuentaResponseDTO cuentaDTO = clienteClient.agregarCuenta(cuenta);
        if(cuentaDTO != null){
            return cuentaDTO;
        }else {
            return null;
        }
    }

    @Transactional
    public String setearTarifas(Double tarifa) {
        String mensaje = monopatinClient.setearTarifas(tarifa);
        return mensaje;
    }

    @Transactional
    public TicketDTO agregarTicket(ViajeDTO viaje) {
        Ticket ticket = new Ticket(viaje);
        adminRepository.save(ticket);
        TicketDTO tDTO = new TicketDTO(ticket);
        clienteClient.descontarViaje(tDTO);
        return tDTO;
    }

    @Transactional
    public ReporteTotalFacturado getTotalFacturado(LocalDateTime mes1, LocalDateTime  mes2) {
        ReporteTotalFacturado rtDTO = new ReporteTotalFacturado();
        rtDTO.setMesInicio(mes1);
        rtDTO.setMesFin(mes2);
        Double totalFacturado = adminRepository.getTotalFacturado(mes1, mes2);
        rtDTO.setTotalFacturado(totalFacturado);
        return rtDTO;
    }
}
