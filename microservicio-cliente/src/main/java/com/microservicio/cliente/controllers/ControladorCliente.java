package com.microservicio.cliente.controllers;

import com.microservicio.cliente.dto.CuentaDto;
import com.microservicio.cliente.dto.UsuarioDto;
import com.microservicio.cliente.dto.ViajeDto;
import com.microservicio.cliente.entities.Cuenta;
import com.microservicio.cliente.entities.Usuario;
import com.microservicio.cliente.services.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ControladorCliente {
    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping("/usar-monopatin/id_usuario/{id_usuario}/ubicacion/{ubicacion}")
    public ResponseEntity<?> usarMonoPatin(@PathVariable("id_usuario")Long id_usuario,@PathVariable("ubicacion")String ubicacion){
        UsuarioDto cuenta=servicioCliente.existeCuenta(id_usuario);
        //en caso de que no encontro la cuenta se retornara un mensaje de error
        if(cuenta==null){
            return ResponseEntity.ok("usted no esta registrado, por favor registre su usuario " +
                    "antes de pedir el servicio del monopatin");
        }
        //en caso de que exista ese usuario entonces si podra pedir el viaje
        ViajeDto viaje=servicioCliente.pedirViaje(id_usuario,ubicacion);

       return ResponseEntity.ok("su viaje: "+viaje.toString()+" ha iniciado con exito ");
    }
    @PostMapping("/registrar-usuario/user/{usuario}")
    public ResponseEntity<?> registrarUsuario(@PathVariable("usuario")Usuario usuario){
        UsuarioDto nuevo=servicioCliente.registrarUsuario(usuario);
        if(nuevo!=null){
            return ResponseEntity.status(HttpStatus.OK).body("Fue registrado con exito el usuario: "+nuevo.getNombre()+" "+nuevo.getApellido());
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde. no pudo registrarse");
     }
     @PostMapping("/agregar-cuenta/cuenta/{cuenta}")
     public ResponseEntity<?> agregarCuenta(@PathVariable("cuenta") Cuenta cuenta){
         CuentaDto nuevo=servicioCliente.registrarCuenta(cuenta);
         if(nuevo!=null){
             return ResponseEntity.status(HttpStatus.OK).body("Fue registrado con exito la cuenta: "+nuevo.getNombre()+", expira en: "+nuevo.getFecha_de_alta());
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde. no pudo registrarse");
     }
     @PutMapping("/asignar-cuenta-a-usuario/cuenta/{id_cuenta}/id_usuario/{id_usuario}")
    public ResponseEntity<?> asignarUsuarioACuenta(@PathVariable("id_cuenta") Long id_cuenta,@PathVariable("id_usuario")Long id_usuario){
         UsuarioDto nuevo=servicioCliente.asignarCuentaAUsuario(id_cuenta,id_usuario);
         if(nuevo!=null){
             return ResponseEntity.status(HttpStatus.OK).body("Fue asignada con exito la cuenta");
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde. no pudo asignarse la cuenta dada al usuario");
     }
     @PutMapping("/descontar-monto/id_cuenta/{id_cuenta}/cobro/{cobro}")
     public ResponseEntity<?> descontarDeLaCuenta(@PathVariable("id_cuenta")Long id_cuenta, @PathVariable("cobro") Float cobro){
         try{
             return ResponseEntity.status(HttpStatus.OK).body(servicioCliente.descontarDeLaCuenta(id_cuenta,cobro));
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo editar, revise los campos e intente nuevamente ");
         }
     }
     @DeleteMapping("/eliminar-cuenta/{id_cuenta}")
     public ResponseEntity<?> deleteCuenta(@PathVariable("id_cuenta") Long id_cuenta) {
         try {
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicioCliente.deleteCuenta(id_cuenta));
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
         }
     }
    @DeleteMapping("/eliminar-usuario/{id_usuario}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id_usuario") Long id_usuario) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicioCliente.deleteUsuario(id_usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
}
