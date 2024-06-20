package com.microservicio.cliente.controllers;

import com.microservicio.cliente.dto.CuentaDTO;
import com.microservicio.cliente.dto.TicketDTO;
import com.microservicio.cliente.dto.UsuarioDto;
import com.microservicio.cliente.models.MonopatinDTO;
import com.microservicio.cliente.models.Viaje;
import com.microservicio.cliente.entities.Cuenta;
import com.microservicio.cliente.entities.Usuario;
import com.microservicio.cliente.services.ServicioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ControladorCliente {
    @Autowired
    private ServicioCliente servicioCliente;
    @GetMapping("/buscar_usuario/{id_usuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("id_usuario")Long id_usuario){
        Usuario user=servicioCliente.buscarUsuario(id_usuario);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/buscar_cuenta/{id_cuenta}")
    public ResponseEntity<?> buscarCuenta(@PathVariable("id_cuenta")Long id_cuenta){
        Cuenta cuenta=servicioCliente.buscarCuenta(id_cuenta);
        return ResponseEntity.ok(cuenta);
    }
    /*Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar
        un monopatín cerca de mi ubicación*/
    @GetMapping("/monopatines-mas-cercanos/ubicacion/{ubicacion}")
    public ResponseEntity<?> getMonopatinesMasCerca(@PathVariable("ubicacion")String ubicacion){
       List<MonopatinDTO> monopatinesCer=servicioCliente.getMonopatinesCercanos(ubicacion);
       if(monopatinesCer.isEmpty()){
           return ResponseEntity.ok("no se encontraron monopatines cercanos a su ubicacion");
       }
        return ResponseEntity.ok(monopatinesCer);
    }
    @GetMapping("/usar-monopatin/{id_usuario}/{ubicacion}")
    public ResponseEntity<?> usarMonoPatin(@PathVariable("id_usuario")Long id_usuario,@PathVariable("ubicacion")String ubicacion){
        UsuarioDto cuenta=servicioCliente.existeCuenta(id_usuario);
        //en caso de que no encontro la cuenta se retornara un mensaje de error
        if(cuenta==null){
            return ResponseEntity.ok("usted no esta registrado, por favor registre su usuario " +
                    "antes de pedir el servicio del monopatin");
        }
        //en caso de que exista ese usuario entonces si podra pedir el viaje
        Viaje viaje=servicioCliente.pedirViaje(id_usuario,ubicacion);

       return ResponseEntity.ok("su viaje: "+viaje.toString()+" ha iniciado con exito ");
    }
    @PostMapping("/registrar-usuario")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
        UsuarioDto nuevo=servicioCliente.registrarUsuario(usuario);
        if(nuevo!=null){
            return ResponseEntity.status(HttpStatus.OK).body("Fue registrado con exito el usuario: "+nuevo.getNombre()+" "+nuevo.getApellido());
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde. no pudo registrarse");
     }
     @PostMapping("/agregar-cuenta")
     public ResponseEntity<CuentaDTO> agregarCuenta(@RequestBody CuentaDTO cuenta){
         CuentaDTO nuevo=servicioCliente.registrarCuenta(cuenta);
         if(nuevo!=null){
             return ResponseEntity.status(HttpStatus.OK).body(nuevo);
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     }
     @PutMapping("/asignar-cuenta-a-usuario/cuenta/{id_cuenta}/idusuario/{id_usuario}")
    public ResponseEntity<?> asignarUsuarioACuenta(@PathVariable("id_cuenta") Long id_cuenta,@PathVariable("id_usuario")Long id_usuario){
         UsuarioDto nuevo=servicioCliente.asignarCuentaAUsuario(id_cuenta,id_usuario);
         if(nuevo!=null){
             return ResponseEntity.status(HttpStatus.OK).body("Fue asignada con exito la cuenta");
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde. no pudo asignarse la cuenta dada al usuario");
     }
     @PutMapping("/descontar-monto/idcuenta/{id_cuenta}/cobro/{cobro}")
     public ResponseEntity<?> descontarDeLaCuenta(@PathVariable("id_cuenta")Long id_cuenta, @PathVariable("cobro") Double cobro){
         try{
             return ResponseEntity.status(HttpStatus.OK).body(servicioCliente.descontarDeLaCuenta(id_cuenta,cobro));
         }catch (Exception e){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo descontar de la cuenta ");
         }
     }
     //inhabilitar cuenta y devuelve como respuesta una cuentaDTO
    @PutMapping("/inhabilitar-cuenta/{id_cuenta}")
    public ResponseEntity<CuentaDTO> inhabilitarCuenta(@PathVariable("id_cuenta")Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicioCliente.inhabilitarCuenta(id_cuenta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/habilitar-cuenta/{id_cuenta}")
    public ResponseEntity<CuentaDTO> habilitarCuenta(@PathVariable("id_cuenta")Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicioCliente.habilitarCuenta(id_cuenta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/descontarViaje")
    public void descontarViaje(@RequestBody TicketDTO ticket) throws Exception {
        Double monto=ticket.getMonto();
        Long id_cuenta=ticket.getIdCuenta();
        servicioCliente.descontarDeLaCuenta(id_cuenta,monto);
    }
     @DeleteMapping("/eliminar-cuenta/idcuenta/{id_cuenta}")
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
