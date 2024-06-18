package com.microservicio.administracion.client;

import com.microservicio.administracion.http.request.CuentaDTO;
import com.microservicio.administracion.http.response.CuentaResponseDTO;
import com.microservicio.administracion.http.response.TicketDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "msvc-cliente" , url = "localhost:5080/api/cliente")
public interface ClienteClient {

    @PutMapping("/inhabilitar-cuenta/{id_cuenta}")
    CuentaResponseDTO inhabilitarCuenta(@PathVariable Long id_cuenta);

    @PostMapping("/agregar-cuenta")
    CuentaResponseDTO agregarCuenta(@RequestBody CuentaDTO cuenta);

    @PutMapping("/habilitar-cuenta/{id_cuenta}")
    CuentaResponseDTO habilitarCuenta(@PathVariable("id_cuenta")Long id_cuenta);

    @PutMapping("/descontarViaje/{ticket}")
    void descontarViaje(@PathVariable TicketDTO ticket);

}
