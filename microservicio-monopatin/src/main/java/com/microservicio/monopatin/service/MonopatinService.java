package com.microservicio.monopatin.service;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository monopatinRepository;
    private Double distancia_cercana = 1000.0;

    @Transactional
    public MonopatinDto save(Monopatin monopatin) {
        Monopatin mAux = monopatinRepository.save(monopatin);
        MonopatinDto mNew = new MonopatinDto(mAux);
        return mNew;
    }

    @Transactional
    public void borrar(Long id) {
        monopatinRepository.deleteById(id);
    }

    @Transactional
    public void mantener(Long id) {
        Optional<Monopatin> o = monopatinRepository.findById(id);
        Monopatin m = o.get();
        m.setEstado("Mantenimiento");
        monopatinRepository.save(m);
    }

    @Transactional
    public void setDispo(Long id) {
        Optional<Monopatin> o = monopatinRepository.findById(id);
        Monopatin m = o.get();
        m.setEstado("Disponible");
        monopatinRepository.save(m);
    }

    @Transactional
    public List<MonopatinDto> getReporteXkm() {
        return monopatinRepository.getReporteXkm();
    }

    @Transactional
    public List<MonopatinDto> getReporteViajes(int cantViajes, int year) {
        return monopatinRepository.getReporteViajes(cantViajes, year);
    }

    @Transactional
    public Integer getDisponibles() {
        return monopatinRepository.getDisponibles();
    }

    @Transactional
    public Integer getNoDisponibles() {
        return monopatinRepository.getNoDisponibles();
    }

    @Transactional
    public List<MonopatinDto> encontrarMonopatinesCercanos(@PathVariable Double latitud, @PathVariable Double longitud) {
        Double distanciaEnKm = this.distancia_cercana / 1000.0;
        List<Monopatin> todosLosMonopatines = monopatinRepository.findAll();
        List<MonopatinDto> lista = new ArrayList<>();
        for (Monopatin m : todosLosMonopatines) {
            if (m.calcularDistancia(latitud, longitud) <= distanciaEnKm) {
                MonopatinDto nuevo = new MonopatinDto(m);
                lista.add(nuevo);
            }
        }
        return lista;
    }

}

