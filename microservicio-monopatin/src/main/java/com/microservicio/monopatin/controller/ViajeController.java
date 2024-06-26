package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.dto.ViajeInicioDto;
import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.service.TarifaService;
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
    @Autowired
    private TarifaService tarifaService;

    @PostMapping("/generar-viaje")
    @ResponseStatus(HttpStatus.CREATED)
    public ViajeDto save(@RequestBody ViajeInicioDto viaje){
        Tarifa t=tarifaService.getTarifa();
        ViajeDto vNew = viajeService.save(viaje,t);
         return  vNew;
    }

    @PutMapping("/finalizar-viaje/{idViaje}")
    @ResponseStatus(HttpStatus.OK)
    public void finViaje(@PathVariable Long idViaje){
        viajeService.finViaje(idViaje);
    }


}
