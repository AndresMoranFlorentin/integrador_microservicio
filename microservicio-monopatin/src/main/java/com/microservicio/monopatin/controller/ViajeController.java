package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    @Autowired
    private ViajeService viajeService;

    @PutMapping("/nuevasTarifas/{tarifa}")
    public String setearTarifas(@PathVariable("tarifa") Double tarifa){
        viajeService.setearTarifas(tarifa);

      return null;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ViajeDto> save(@RequestBody Viaje viaje){
        ViajeDto vNew = viajeService.save(viaje);
        return ResponseEntity.ok(vNew);
    }

    @PutMapping("/fin/{idViaje}")
    @ResponseStatus(HttpStatus.OK)
    public void finViaje(@PathVariable Long idViaje){
        viajeService.finViaje(idViaje);
    }


}
