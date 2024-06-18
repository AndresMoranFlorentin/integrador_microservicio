package com.microservicio.monopatin.client;

import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.model.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msvc-administracion" , url = "http://localhost:9080/api/admin")
public interface AdministracionClient {
    @PostMapping("api/administracion/generarTicket/{viaje}")
    void generarTicket(@PathVariable ViajeDto viaje);

}
