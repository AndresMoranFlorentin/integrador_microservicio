package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.service.MonopatinService;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;


    @PostMapping("/nuevo")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMonopatin(@RequestBody Monopatin monopatin){
        monopatinService.save(monopatin);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void borrar(@PathVariable Long id){
        monopatinService.borrar(id);
    }


}
