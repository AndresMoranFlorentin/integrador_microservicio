package com.microservicio.administracion.client;

import com.microservicio.administracion.http.response.MonopatinDto;
import com.microservicio.administracion.http.response.ReporteMonopatinesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-monopatin" , url = "localhost:8080/api/")/*Aca iria el endpoint de Monopatin*/
public interface MonopatinClient {



    @GetMapping("monopatin/reporteViajes/{cantViajes}/{year}")
    List<MonopatinDto> getMonopatinesConMasViajes(@PathVariable int cantViajes, @PathVariable int year);

    @GetMapping("monopatin/disponiblesVsIndisponibles")
    ReporteMonopatinesDTO getDisponibles();

    @GetMapping("monopatin/monopatines-cercanos/latitud/{latitud}/longitud/{longitud}")
    List<MonopatinDto> getMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud);

    @PutMapping("tarifa/actualizar-tarifa/{tarifa}")
    String setearTarifas(@PathVariable Double tarifa);

    @PutMapping("monopatin/mantener/{id}")
    void mantener(@PathVariable Long id);
    @PostMapping("monopatin/generar-monopatin/{modelo}")
    public MonopatinDto save(@PathVariable String modelo);

}
