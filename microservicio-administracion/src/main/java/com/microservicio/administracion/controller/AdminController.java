package com.microservicio.administracion.controller;

import com.microservicio.administracion.service.AdminService;
import com.microservicio.administracion.service.dto.request.AdministradorRequestDTO;
import com.microservicio.administracion.service.dto.response.AdministradorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/{id_admin}")
    public ResponseEntity<?> getAdministrador(@PathVariable Long id_admin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdministrador(id_admin));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado.\"}");
        }
    }

    @PostMapping("darDeAlta")
    public ResponseEntity<?> save(@RequestBody @Valid AdministradorRequestDTO request){
        AdministradorResponseDTO result = this.adminService.save(request);
        return ResponseEntity.accepted().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AdministradorRequestDTO entity){
        AdministradorResponseDTO result = this.adminService.save(entity);
        return ResponseEntity.accepted().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            boolean result = adminService.delete(id);
            if (result) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Estudiante no encontrado.\"}");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
