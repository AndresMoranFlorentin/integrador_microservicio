package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.dto.MonopatinDtoConPausa;
import com.microservicio.monopatin.dto.MonopatinDtoNuevo;
import com.microservicio.monopatin.dto.ReporteMonopatinesDto;
import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;


    @PostMapping("/generar-monopatin/{modelo}")
    @ResponseStatus(HttpStatus.CREATED)
    public MonopatinDto save(@PathVariable String modelo){
        MonopatinDto mNew = monopatinService.save(modelo);
        return mNew;
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

    @GetMapping("/monopatin/{id}")
    public MonopatinDto getMonopatin(@PathVariable Long id) {
        return monopatinService.getMonopatin(id);
    }

    @PutMapping("/disponible/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void setDispo(@PathVariable Long id){
        monopatinService.setDispo(id);
    }

    @GetMapping("/reporteXkm")
    public ResponseEntity<List<MonopatinDto>> getReporteXkm() {
        List<MonopatinDto> m = monopatinService.getReporteXkm();
        if (m.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }
    @GetMapping("/reporteXkmConPausa")
    List<MonopatinDtoConPausa> getMonopatinesPorKmConPausa(){
        return monopatinService.getMonopatinesPorKmConPausa();
    }

    @GetMapping("/reporteViajes/{cantViajes}/{year}")
    public ResponseEntity<List<MonopatinDto>> getReporteViajes(@PathVariable int cantViajes,@PathVariable int year){
        List<MonopatinDto> m = monopatinService.getReporteViajes(cantViajes, year);
        if (m.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(m);
    }

    //devuelve una lista de todos los monopatines que esten disponibles
    // junto a los que no están disponibles
    @GetMapping("/disponiblesVsIndisponibles")
    public ResponseEntity<ReporteMonopatinesDto> getDisponibles() {
        Integer monopatinesDisponibles = monopatinService.getDisponibles();
        Integer monopatinesIndisponibles = monopatinService.getNoDisponibles();
        ReporteMonopatinesDto reporte = new ReporteMonopatinesDto();
        reporte.setOperativos(monopatinesDisponibles);
        reporte.setEnMantenimiento(monopatinesIndisponibles);

        return ResponseEntity.ok(reporte);
    }

    //devuelve una lista de monopatines cercanos a una zona dada del usuario(esto lo usaria usuario)
    @GetMapping("/monopatines-cercanos/latitud/{latitud}/longitud/{longitud}")
    public List<MonopatinDto> obtenerMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud) {
        return monopatinService.encontrarMonopatinesCercanos(latitud, longitud);
    }





}
