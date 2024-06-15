package com.microservicio.administracion.client;

import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.response.CuentaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-cliente" , url = "localhost:5080/api/cliente")
public interface ClienteClient {

    @GetMapping("/desactivar/{id_cuenta}")
    public CuentaResponseDTO inhabilitarCuenta(@PathVariable Long id_cuenta);

    @GetMapping("/activar/{id_cuenta}")
    public CuentaResponseDTO habilitarCuenta(@PathVariable Long id_cuenta);

    @PostMapping("/agregar-cuenta")
    public CuentaResponseDTO agregarCuenta(@RequestBody CuentaDTO cuenta);

}
