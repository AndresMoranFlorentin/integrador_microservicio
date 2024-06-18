package com.microservicio.administracion.controller;

import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.request.ViajeDTO;
import com.microservicio.administracion.http.response.*;
import com.microservicio.administracion.service.AdminService;
import com.microservicio.administracion.http.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PutMapping("/mantenerMonopatin/{id_monopatin}")
    public void mantenerMonopatin(@PathVariable("id_monopatin") Long id_monopatin) {
        adminService.mantenerMonopatin(id_monopatin);
    }

    /*
    *   3b) Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la
    *   misma
    */

    @PutMapping("/setearTarifa/{tarifa}")
    public String setearTarifa(@PathVariable("tarifa") Double tarifa) {
        String mensaje = adminService.setearTarifas(tarifa);
        return mensaje;
    }

    @PutMapping("/desactivarCuenta/{id_cuenta}")
    public ResponseEntity<?> desabilitarCuenta(@PathVariable("id_cuenta") Long id_cuenta) {
        CuentaResponseDTO cDTO = adminService.inhabilitarCuenta(id_cuenta);
        return ResponseEntity.ok(cDTO);
    }

    @PutMapping("/activarCuenta/{id_cuenta}")
    public CuentaResponseDTO activarCuenta(@PathVariable("id_cuenta") Long id_cuenta) {
        CuentaResponseDTO cDTO = adminService.habilitarCuenta(id_cuenta);
        return cDTO;
    }

    @PostMapping("/agregar-cuenta")
    public CuentaResponseDTO agregarCuenta(@RequestBody CuentaDTO cuenta) {
        CuentaResponseDTO cDTO = adminService.agregarCuenta(cuenta);
        return cDTO;
    }

    /*
    *    3c) Como administrador quiero consultar los monopatines con más de X
    *    viajes en un cierto año.
    */
    @GetMapping("/monopatines/{minViajes}/{year}")
    public ResponseEntity<?> getMonopatinesConMasViajes(@PathVariable int minViajes, @PathVariable int year) {
        MonopatinconXViajesResponseDTO monopatines = adminService.getMonopatinesConMasViajes(minViajes, year);
        return ResponseEntity.ok(monopatines);
    }

    /*
     *   3d) Como administrador quiero consultar el total facturado
     *       en un rango de meses de cierto año
     */
    @GetMapping("/totalFacturado/{mes1}/{mes2}")
    public ResponseEntity<?> getTotalFacturado(@PathVariable LocalDateTime mes1, @PathVariable LocalDateTime  mes2){
        ReporteTotalFacturado rTF = adminService.getTotalFacturado(mes1, mes2);
        return ResponseEntity.ok(rTF);
    }


    /*
    *   3e) Como administrador quiero consultar la cantidad de monopatines actualmente
    *    en operación, versus la cantidad de monopatines actualmente en mantenimiento
    */
    @GetMapping("/reporteMonopatines/")
    public ResponseEntity<?> getReporteMonopatines(){
        ReporteMonopatinesDTO monopatines = adminService.getReporteMonopatines();
        return ResponseEntity.ok(monopatines);
    }

    /*
    *  3g) Listado de los monopatines cercanos a mi zona, para poder encontrar
    *    un monopatín cerca de mi ubicación
    */
    @GetMapping("/monopatines-mas-cercanos/{ubicacion}")
    public List<MonopatinDTO> getMonopatinesMasCercanos(@PathVariable("ubicacion") String ubicacion){
        List<MonopatinDTO> monopatinesCercanos = adminService.getMonopatinesCercanos(ubicacion);
        return monopatinesCercanos;
    }

    @PostMapping("api/administracion/generarTicket/{viaje}")
    public TicketDTO generarTicket(@PathVariable ViajeDTO viaje){
        TicketDTO tDTO = adminService.agregarTicket(viaje);
        return tDTO;
    }
}
