package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/tarifa")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @PutMapping("/actualizar-tarifa/{tarifa}")
    public String actualizarTarifa(@PathVariable Double tarifa) {
        return tarifaService.actualizarTarifa(tarifa);
    }

}
