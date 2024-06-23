package com.microservicio.mantenimiento.feignClient;


import com.microservicio.mantenimiento.model.MonopatinDto;
import com.microservicio.mantenimiento.model.MonopatinDtoConPausa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="msvc-monopatin", url = "http://localhost:8080/api/monopatin")
public interface MonopatinFeignClient {

    @PostMapping("/save")
    MonopatinDto save(@RequestBody MonopatinDto monopatin);

    @GetMapping("/{id_monopatin}")
    MonopatinDto getMonopatin(@PathVariable("id_monopatin") Long id_monopatin);

    @PutMapping("/disponible/{id}")
    void setDispo(@PathVariable("id") Long id);

    @GetMapping("/reporteXkm")
    List<MonopatinDto> getMonopatinesPorKm();
    @GetMapping("/reporteXkmConPausa")
    List<MonopatinDtoConPausa> getMonopatinesPorKmConPausa();
}