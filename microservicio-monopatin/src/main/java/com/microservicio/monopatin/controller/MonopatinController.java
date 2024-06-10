package com.microservicio.monopatin.controller;

import com.microservicio.monopatin.service.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monopatin")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

}
