package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.service.MonopatinService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MonopatinDto> save(@RequestBody Monopatin monopatin){
        MonopatinDto mNew = monopatinService.save(monopatin);
        return ResponseEntity.ok(mNew);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrar(@PathVariable Long id){
        monopatinService.borrar(id);
    }

    @PutMapping("/mantener/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void mantener(@PathVariable Long id){
        monopatinService.mantener(id);
    }

    @PutMapping("/disponible/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void setDispo(@PathVariable Long id){
        monopatinService.setDispo(id);
    }

    @PutMapping("/tarifa/{tarifa}")
    @ResponseStatus(HttpStatus.OK)
    public void setTarifa(@PathVariable Double tarifa){
        monopatinService.setTarifa(tarifa);
    }

    @PutMapping("/tarifaExtra/{tarifa}")
    @ResponseStatus(HttpStatus.OK)
    public void setTarifaExtra(@PathVariable Double tarifa){
        monopatinService.setTarifaExtra(tarifa);
    }

    @GetMapping("/reporteXkm")
    public ResponseEntity<List<MonopatinDto>> getReporteXkm() {
        List<MonopatinDto> m = monopatinService.getReporteXkm();
        if (m.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }

    @GetMapping("/reporteViajes/{cantViajes}{año}")
    public ResponseEntity<List<MonopatinDto>> getReporteViajes(@PathVariable int cantViajes, int year){
        List<MonopatinDto> m = monopatinService.getReporteViajes(cantViajes, year);
        if (m.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<MonopatinDto>> getDisponibles(){
        List<MonopatinDto> m = monopatinService.getDisponibles();
        if (m.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }

    @GetMapping("/getTarifa")
    public int getTarifa(){
        return monopatinService.getTarifa();
    }

    @GetMapping("/getTarifa")
    public int getTarifaExtra(){
        return monopatinService.getTarifaExtra();
    }







}
