package com.microservicio.administracion.client;

import com.microservicio.administracion.http.request.MonopatinEnMantenimientoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msvc-mantenimiento" , url = "localhost:8060/api/mantenimiento")
public interface MantenimientoClient {

    @PostMapping("/inicioMantenimiento")
    void guardarInicioMantenimiento(MonopatinEnMantenimientoDTO mEMDTO);
}
