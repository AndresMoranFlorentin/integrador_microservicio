package com.microservicio.cliente.clients;

import com.microservicio.cliente.dto.ViajeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "msvc-administracion", url="localhost:9080/api/admin")
public interface ClientFeignAdministracion {

    @GetMapping("/pedir-viaje/{id_usuario}/{ubicacion}")
    public ViajeDto pedirViaje(Long id_usuario,String ubicacion);

}
