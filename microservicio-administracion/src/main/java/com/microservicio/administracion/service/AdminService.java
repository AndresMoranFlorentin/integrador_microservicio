package com.microservicio.administracion.service;

import com.microservicio.administracion.client.ClienteClient;
import com.microservicio.administracion.client.MantenimientoClient;
import com.microservicio.administracion.client.MonopatinClient;
import com.microservicio.administracion.entity.Ticket;
import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.request.MonopatinEnMantenimientoDTO;
import com.microservicio.administracion.http.request.ViajeDTO;
import com.microservicio.administracion.http.response.*;
import com.microservicio.administracion.repository.AdminRepository;
import com.microservicio.administracion.http.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import com.microservicio.administracion.service.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Autowired
    private MantenimientoClient mantenimientoClient;


    @Transactional
    public void mantenerMonopatin(MonopatinEnMantenimientoDTO mEMDTO) {
        monopatinClient.mantener(mEMDTO.getId_monopatin());
        mantenimientoClient.guardarInicioMantenimiento(mEMDTO);
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
    public List<MonopatinDTO> getMonopatinesCercanos(Double latitud, Double longitud) {
        List<MonopatinDTO> monopatinesCercanos = monopatinClient.getMonopatinesCercanos(latitud, longitud);
        return monopatinesCercanos;
    }

    @Transactional
    public MonopatinconXViajesResponseDTO getMonopatinesConMasViajes(int cantViajes, int year ){
        List<MonopatinDTO> monopatines = monopatinClient.getMonopatinesConMasViajes(cantViajes, year);
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
    public String setearTarifas(@PathVariable Double tarifa) {
        String mensaje = monopatinClient.setearTarifas(tarifa);
        return mensaje;
    }

    @Transactional
    public TicketDTO agregarTicket(@RequestBody ViajeDTO viaje) {
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
