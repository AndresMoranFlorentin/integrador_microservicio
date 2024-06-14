package com.microservicio.administracion.client;

import com.microservicio.administracion.http.response.MonopatinDTO;
import com.microservicio.administracion.http.response.ReporteMonopatinesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-monopatin" , url = "localhost:9090/api/monopatin")/*Aca iria el endpoint de Monopatin*/
public interface MonopatinClient {

    @GetMapping("/monopatinesSarasa/{year}/{minViajes}")/*Reemplazar por el endpoint de Monopatin*/
    List<MonopatinDTO> getMonopatinesConMasViajes(@PathVariable int year, @PathVariable int minViajes);

    @GetMapping("/reporteMonopatines")/*Reemplazar por el endpoint de Monopatin*/
    ReporteMonopatinesDTO getReporteMonopatines();

    @GetMapping("/monopatinesCercanos/{ubicacion}")/* Reemplazar por el endpoint de Monopatin*/
    List<MonopatinDTO> getMonopatinesCercanos(@PathVariable String ubicacion);
}
