package com.microservicio.cliente.clients;

import com.microservicio.cliente.dto.MonopatinDto;
import com.microservicio.cliente.dto.ViajeDto;
import com.microservicio.cliente.dto.ViajeInicioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-monopatin", url="http://localhost:8080/api/")

public interface ClientMonopatin {
    @PostMapping("viaje/generar-viaje")
     public ViajeDto save(@RequestBody ViajeInicioDto viaje);
    @PutMapping("viaje/finalizar-viaje/{idViaje}")
    void finViaje(@PathVariable Long idViaje);
    @GetMapping("monopatin/monopatines-cercanos/latitud/{latitud}/longitud/{longitud}")
    List<MonopatinDto> getMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud);


}
