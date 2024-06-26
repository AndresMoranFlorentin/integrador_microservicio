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
    public String actualizarTarifa(Double tarifa) {
        Optional<Tarifa> o = tarifaRepository.findById(ID);
        Tarifa t = o.get();
        t.setPrecio(tarifa);
        Tarifa tarif= tarifaRepository.save(t);
        if(tarif!=null){
            return "se actualizo con exito la tarifa";
        }
        else{
            return " has fracasado miserablemente";
        }
    }

    public void save(Tarifa t) {
        tarifaRepository.save(t);
    }

    public Tarifa getTarifa() {
        Optional<Tarifa> t=tarifaRepository.findById(ID);
        Tarifa tarifa=t.get();
        return tarifa ;
    }
}

