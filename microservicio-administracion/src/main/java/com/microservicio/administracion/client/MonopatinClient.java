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



    @GetMapping("/reporteDeViajePorAnioX/{cantViajes}/{anio}")
    List<MonopatinDTO> getMonopatinesConMasViajes(@PathVariable int cantViajes, @PathVariable int anio);

    @GetMapping("/disponiblesVsIndisdisponibles")
    ReporteMonopatinesDTO getDisponibles();

    @GetMapping("/monopatines-cercanos/latitud/{latitud}/longitud/{longitud}")
    List<MonopatinDTO> getMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud);

    @PutMapping("/nuevasTarifas/{tarifa}")
    String setearTarifas(Double tarifa);

    @PutMapping("/mantener/{id}")
    void mantener(Long id);
}
