package com.microservicio.monopatin.client;

import com.microservicio.monopatin.dto.ViajeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-administracion" , url = "http://localhost:8080/api/admin")
public interface AdministracionClient {
    @PostMapping("/generarTicket")
    void generarTicket(@RequestBody ViajeDto viaje);

}
