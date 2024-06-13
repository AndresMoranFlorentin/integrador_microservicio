package com.microservicio.mantenimiento.feignClient;


import com.microservicio.mantenimiento.entity.Mantenimiento;
import com.microservicio.mantenimiento.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@FeignClient(name="microservicio-monopatin")
public interface MonopatinFeignClient {

    @PostMapping("bikes")
    Monopatin save(@RequestBody Monopatin monopatin);

    @GetMapping("bikes/byUser/{userId}")
    List<Monopatin> getBikes(@PathVariable("id_monopatin") Long userId);

}