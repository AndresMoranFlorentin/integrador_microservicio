package com.microservicio.monopatin.service;

import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    private final Long ID = 1L;

    @Transactional
    public Tarifa actualizarTarifa(Tarifa tarifa) {
        Optional<Tarifa> o = tarifaRepository.findById(ID);
        Tarifa t = o.get();
        t.setPrecio(tarifa.getPrecio());
        return tarifaRepository.save(t);
    }
}

