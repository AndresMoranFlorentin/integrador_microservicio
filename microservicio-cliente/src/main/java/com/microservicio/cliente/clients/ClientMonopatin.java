package com.microservicio.cliente.clients;

import com.microservicio.cliente.dto.ViajeDto;
import com.microservicio.cliente.dto.ViajeInicioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-monopatin", url="http://localhost:8080/api/viaje")

public interface ClientMonopatin {
    @PostMapping("/generar-viaje")
     public ViajeDto save(@RequestBody ViajeInicioDto viaje);
    @PutMapping("/finalizar-viaje/{idViaje}")
    void finViaje(@PathVariable Long idViaje);
}
