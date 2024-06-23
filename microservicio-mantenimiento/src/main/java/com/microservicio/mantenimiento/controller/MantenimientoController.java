package com.microservicio.mantenimiento.controller;

import com.microservicio.mantenimiento.model.MantenimientoMonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinEnMantenimientoDTO;
import com.microservicio.mantenimiento.service.MantenimientoService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/mantenimiento")
public class MantenimientoController {
    @Autowired
    private MantenimientoService mantService;

    @PostMapping("/add_mant")
    public ResponseEntity<?> save(@RequestBody MantenimientoMonopatinDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.SC_OK).body(mantService.save(entity));
        }catch (Exception e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping("/inicioMantenimiento")
    public void guardarInicioMantenimiento(@RequestBody MonopatinEnMantenimientoDTO mEMDTO) throws Exception {
                mantService.guardar(mEMDTO);
    }

    @PutMapping("/liberar_monopatin/{id_monopatin}")
    public void setDispo(@PathVariable("id_monopatin") Long id_monopatin) throws Exception {
        mantService.setDispo(id_monopatin);
    }

    @GetMapping("/monopatines_por_km/{pausa}")
    public ResponseEntity<?> getMonopatinsPorKm(@PathVariable boolean pausa) {
        try {
            return ResponseEntity.status(HttpStatus.SC_OK).body(mantService.getMonopatinesPorKm(pausa));
        }catch (Exception e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo generar el reporte.\"}");
        }
    }
}
