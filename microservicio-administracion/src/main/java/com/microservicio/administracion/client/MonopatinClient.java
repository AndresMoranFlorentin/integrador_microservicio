package com.microservicio.administracion.client;

import com.microservicio.administracion.http.response.MonopatinDTO;
import com.microservicio.administracion.http.response.ReporteMonopatinesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "msvc-monopatin" , url = "localhost:9090/api/monopatin")/*Aca iria el endpoint de Monopatin*/
public interface MonopatinClient {

    @GetMapping("/reporteViajes/{cantViajes}/{año}")
    List<MonopatinDTO> getMonopatinesConMasViajes(@PathVariable int cantViajes, @PathVariable int año);

    @GetMapping("/reporteMonopatines")
    ReporteMonopatinesDTO getReporteMonopatines();

    @GetMapping("/monopatinesCercanos/{ubicacion}")
    List<MonopatinDTO> getMonopatinesCercanos(@PathVariable String ubicacion);

    @PutMapping("/nuevasTarifas/{tarifa}")
    String setearTarifas(Double tarifa);
}
