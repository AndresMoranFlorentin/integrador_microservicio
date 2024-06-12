package com.microservicio.monopatin.service;

import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;

    @Transactional
    public void save(Monopatin monopatin) {
        monopatinRepository.save(monopatin);
    }

    @Transactional
    public void borrar(Long id) {
        monopatinRepository.deleteById(id);
    }

}
