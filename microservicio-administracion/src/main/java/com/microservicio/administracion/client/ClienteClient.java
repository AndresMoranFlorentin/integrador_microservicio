package com.microservicio.administracion.client;

import com.microservicio.administracion.http.response.CuentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente" , url = "localhost:5080/api/cliente")
public interface ClienteClient {

    @GetMapping("/desactivar/{id_cuenta}")
    CuentaDTO desactivarCuenta(@PathVariable Long id_cuenta);

    @GetMapping("/activar/{id_cuenta}")
    CuentaDTO activarCuenta(@PathVariable Long id_cuenta);
}
