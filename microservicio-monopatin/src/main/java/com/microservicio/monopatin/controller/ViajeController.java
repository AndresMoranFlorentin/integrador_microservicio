package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @PostMapping("/generar-viaje")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ViajeDto> save(@RequestBody Viaje viaje){
        ViajeDto vNew = viajeService.save(viaje);
        return ResponseEntity.ok(vNew);
    }

    @PutMapping("/finalizar-viaje/{idViaje}")
    @ResponseStatus(HttpStatus.OK)
    public void finViaje(@PathVariable Long idViaje){
        viajeService.finViaje(idViaje);
    }


}
