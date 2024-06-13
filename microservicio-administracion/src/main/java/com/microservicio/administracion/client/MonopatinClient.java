package com.microservicio.administracion.client;

import com.microservicio.administracion.service.dto.response.MonopatinResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-monopatin" , url = "localhost:9090/api/")/*Aca iria el endpoint de Monopatin*/
public interface MonopatinClient {

    @GetMapping("/search-by-course/{year}/{minViajes}")/*Reemplazar por el endpoint de Monopatin*/
    List<MonopatinResponseDTO> getMonopatinesConMasViajes(@PathVariable int year, @PathVariable int minViajes);
}
