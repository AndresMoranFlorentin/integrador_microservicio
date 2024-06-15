package com.microservicio.monopatin.service;

import com.microservicio.monopatin.client.AdministracionClient;
import com.microservicio.monopatin.controller.MonopatinController;
import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ViajeService {

    final Double LIMITEPAUSA = 0.25;

    @Autowired
    private ViajeRepository viajeRepository;
    @Autowired
    private AdministracionClient adminClient;
    @Autowired
   // private MonopatinController monopatinController;


    @Transactional
    public ViajeDto save(Viaje viaje) {
        Viaje vAux = viajeRepository.save(viaje);
        ViajeDto vNew = new ViajeDto(vAux);
        return vNew;
    }

    @Transactional
    public void finViaje(Long idViaje){
        int tarifa = monopatinController.getTarifa();
        int tarifaExtra = monopatinController.getTarifaExtra();
        Tarifa tarifa=viajeRepository;
        Optional<Viaje> o = viajeRepository.findById(idViaje);
        Viaje v = o.get();
        v.setFin();
        if (v.getConPausa() && (v.getTiempoPausado()> LIMITEPAUSA)){
            v.setCosto(tarifaExtra);
            viajeRepository.save(v);
            adminClient.generarTicket(v);
        }else {
            v.setCosto(tarifa);
            viajeRepository.save(v);
            adminClient.generarTicket(v);
        }
    }


    public void setearTarifas(Double tarifa) {
       Optional<Viaje> viaje =viajeRepository.findById(1L);
       viaje.get().setTarifa(tarifa);
    }
}
