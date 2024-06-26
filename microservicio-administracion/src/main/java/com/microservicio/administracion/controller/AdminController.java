package com.microservicio.administracion.controller;

import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.request.MonopatinEnMantenimientoDTO;
import com.microservicio.administracion.http.request.ViajeDTO;
import com.microservicio.administracion.http.response.*;
import com.microservicio.administracion.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     *
     * Agregar nuevo monopatin
     */
    @PostMapping("/crear-monopatin/{modelo}")
    public MonopatinDto crearMonopatin(@PathVariable("modelo") String modelo){
        return adminService.crearMonopatin(modelo);
    }

    /*
     * Registrar fin de mantenimiento de monopatín
    */
    @PutMapping("/mantenerMonopatin/")
    public void mantenerMonopatin(@RequestBody MonopatinEnMantenimientoDTO mEMDTO) {
        adminService.mantenerMonopatin(mEMDTO);
    }


    @PutMapping("/setearTarifa/{tarifa}")
    public String setearTarifa(@PathVariable("tarifa") Double tarifa) {
        String mensaje = adminService.setearTarifas(tarifa);
        return mensaje;
    }

    /*
     *   3b) Como administrador quiero poder reactivar cuentas para habilitar el uso momentáneo de la
     *   misma
     */
    @PutMapping("/desactivarCuenta/{id_cuenta}")
    public ResponseEntity<?> desabilitarCuenta(@PathVariable("id_cuenta") Long id_cuenta) {
        CuentaResponseDTO cDTO = adminService.inhabilitarCuenta(id_cuenta);
        return ResponseEntity.ok(cDTO);
    }
    /*
     *   3b) Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la
     *   misma
     */
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
    @GetMapping("/monopatines/{cantViajes}/{year}")
    public ResponseEntity<List<MonopatinDto>> getMonopatinesConMasViajes(@PathVariable int cantViajes, @PathVariable int year) {
        return ResponseEntity.ok( adminService.getMonopatinesConMasViajes(cantViajes, year));
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
    public ResponseEntity<?> getReporteMonopatines() {
        ReporteMonopatinesDTO monopatines = adminService.getReporteMonopatines();
        return ResponseEntity.ok(monopatines);
    }

    @PostMapping("/generarTicket")
    public TicketDTO generarTicket(@RequestBody ViajeDTO viaje){
        TicketDTO tDTO = adminService.agregarTicket(viaje);
        return tDTO;
    }
}
