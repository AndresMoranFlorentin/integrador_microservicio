package com.microservicio.monopatin.service;

import com.microservicio.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

}
