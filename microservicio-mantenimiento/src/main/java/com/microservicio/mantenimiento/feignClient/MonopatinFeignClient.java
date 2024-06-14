package com.microservicio.mantenimiento.feignClient;


import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient(name="microservicio-monopatin", url = "http://localhost:9090/api/monopatin")
public interface MonopatinFeignClient {

    @PostMapping("monopatin")
    Monopatin save(@RequestBody Monopatin monopatin);

    @GetMapping("monopatin/{id_monopatin}")
    List<Monopatin> getMonopatin(@PathVariable("id_monopatin") Long userId);

}