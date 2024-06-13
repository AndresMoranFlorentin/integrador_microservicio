package com.microservicio.cliente.clients;

import com.microservicio.cliente.models.Monopatin;
import com.microservicio.cliente.models.Viaje;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-administracion", url="http://localhost:9080/api/admin")
public interface ClientFeignAdministracion {
    @GetMapping("/pedir-viaje/{id_usuario}/{ubicacion}")
    public Viaje pedirViaje(@PathVariable("id_usuario")Long id_usuario, @PathVariable("ubicacion") String ubicacion);
    @GetMapping("/monopatines-mas-cercanos/{ubicacion}")
    public List<Monopatin> getMonopatinesMasCercanos(@PathVariable("ubicacion") String ubicacion);
}
