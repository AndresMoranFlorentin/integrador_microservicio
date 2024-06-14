package com.microservicio.monopatin.service;

import com.microservicio.monopatin.controller.MonopatinController;
import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private MonopatinController monopatinController;


    @Transactional
    public ViajeDto save(Viaje viaje) {
        Viaje vAux = viajeRepository.save(viaje);
        ViajeDto vNew = new ViajeDto(vAux);
        return vNew;
    }

    @Transactional
    public void finViaje(Long idViaje){
        int tarifa = monopatinController.getTarifa();
        Optional<Viaje> o = viajeRepository.findById(idViaje);
        Viaje v = o.get();
        v.setFin();
        v.setCosto(tarifa);
        viajeRepository.save(v);
    }


}
