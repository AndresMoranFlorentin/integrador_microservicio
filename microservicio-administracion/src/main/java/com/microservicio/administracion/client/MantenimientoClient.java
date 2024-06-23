package com.microservicio.administracion.client;

import com.microservicio.administracion.http.request.MonopatinEnMantenimientoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-mantenimiento" , url = "localhost:8080/api/mantenimiento")
public interface MantenimientoClient {

    @PostMapping("/inicioMantenimiento")
    void guardarInicioMantenimiento(@RequestBody MonopatinEnMantenimientoDTO mEMDTO);
}
