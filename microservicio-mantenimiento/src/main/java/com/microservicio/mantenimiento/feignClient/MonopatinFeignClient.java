package com.microservicio.mantenimiento.feignClient;


import com.microservicio.mantenimiento.model.MonopatinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-monopatin", url = "http://localhost:9090/api/monopatin")
public interface MonopatinFeignClient {

    @PostMapping("monopatin")
    MonopatinDto save(@RequestBody MonopatinDto monopatin);

    @GetMapping("monopatin/{id_monopatin}")
    List<MonopatinDto> getMonopatin(@PathVariable("id_monopatin") Long id_monopatin);

    @PutMapping("monopatin/liberarMantenimiento/{id_monopatin}")
    MonopatinDto setLiberarMonopatin(@PathVariable("id_monopatin") Long id_monopatin);

    @GetMapping("monopatin/reporte_monopatines_por_km")
    List<MonopatinDto> getMonopatinesPorKm();
}