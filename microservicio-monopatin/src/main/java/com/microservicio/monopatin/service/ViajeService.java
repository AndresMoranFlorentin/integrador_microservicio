package com.microservicio.monopatin.service;

import com.microservicio.monopatin.client.AdministracionClient;
import com.microservicio.monopatin.controller.MonopatinController;
import com.microservicio.monopatin.dto.ViajeDto;
import com.microservicio.monopatin.dto.ViajeInicioDto;
import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.repository.TarifaRepository;
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


    @Transactional
    public ViajeDto save(ViajeInicioDto dto, Tarifa t) {
        Viaje viaje = new Viaje(dto.getIdMonopatin(), dto.getIdCuenta(), dto.getIdUsuario(), t);
        Viaje guardado = viajeRepository.save(viaje);
        ViajeDto vNew = new ViajeDto(guardado);
        return vNew;
    }

    @Transactional
    public void finViaje(Long idViaje) {
        Tarifa tarifa = viajeRepository.findById(idViaje).get().getTarifa();
        Optional<Viaje> o = viajeRepository.findById(idViaje);
        Viaje v = o.get();
        Double tarifaPrecio = tarifa.getPrecio();
        Double tarifaExtra = (tarifaPrecio * (v.getTarifaExtra() + 1));
        v.setFin();
        if (v.getConPausa() && (v.getTiempoPausado() > LIMITEPAUSA)) {
            v.setCosto(tarifaExtra);
            viajeRepository.save(v);
            ViajeDto viajeDto = new ViajeDto(v);
          //  adminClient.generarTicket(viajeDto);
        } else {
            v.setCosto(tarifaPrecio);
            viajeRepository.save(v);
            ViajeDto viajeDto = new ViajeDto(v);
          //  adminClient.generarTicket(viajeDto);
        }
    }

}