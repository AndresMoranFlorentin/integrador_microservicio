package com.microservicio.mantenimiento.controller;

import com.microservicio.mantenimiento.model.Monopatin;
import com.microservicio.mantenimiento.service.MantenimientoService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {
    @Autowired
    private MantenimientoService mantService;

    @PostMapping("/add_mant")
    public ResponseEntity<?> save(@RequestBody Monopatin entity) {
        try {
            return ResponseEntity.status(HttpStatus.SC_OK).body(mantService.save(entity));
        }catch (Exception e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
}
