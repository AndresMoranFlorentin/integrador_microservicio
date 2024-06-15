package com.microservicio.administracion.controller;

import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.response.CuentaResponseDTO;
import com.microservicio.administracion.http.response.MonopatinDTO;
import com.microservicio.administracion.http.response.ReporteMonopatinesDTO;
import com.microservicio.administracion.service.AdminService;
import com.microservicio.administracion.http.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import com.microservicio.administracion.http.response.MonopatinconXViajesResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*
    *   3b) Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la
    *   misma
    */
    @GetMapping("/desactivarCuenta/{id_cuenta}")
    public ResponseEntity<?> desabilitarCuenta(@RequestParam @PathVariable Long id_cuenta) {
        CuentaResponseDTO cDTO = adminService.inhabilitarCuenta(id_cuenta);
        return ResponseEntity.ok(cDTO);
    }

    @GetMapping("/activarCuenta/{id_cuenta}")
    public CuentaResponseDTO activarCuenta(@RequestParam @PathVariable Long id_cuenta) {
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
    @GetMapping("/monopatines/{year}/{minViajes}")
    public ResponseEntity<?> getMonopatinesConMasViajes(@RequestParam int year, @RequestParam int minViajes) {
        MonopatinconXViajesResponseDTO monopatines = adminService.getMonopatinesConMasViajes(year, minViajes);
        return ResponseEntity.ok(monopatines);
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

    /*CRUD ADMIN*/
    @GetMapping("/{id_admin}")
    public ResponseEntity<?> getAdministrador(@PathVariable Long id_admin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdministrador(id_admin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado.\"}");
        }
    }

    @PostMapping("darDeAlta")
    public ResponseEntity<?> save(@RequestBody @Valid AdministradorRequestDTO request){
        AdministradorResponseDTO result = this.adminService.save(request);
        return ResponseEntity.accepted().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AdministradorRequestDTO entity) throws Exception {
        AdministradorResponseDTO result = this.adminService.update(id, entity);
        return ResponseEntity.accepted().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            boolean result = adminService.delete(id);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Estudiante no encontrado.\"}");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
