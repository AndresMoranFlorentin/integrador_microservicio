package com.microservicio.monopatin.service;

import com.microservicio.monopatin.dto.MonopatinDto;
import com.microservicio.monopatin.dto.MonopatinDtoConPausa;
import com.microservicio.monopatin.dto.MonopatinDtoNuevo;
import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MonopatinDto save(String modelo) {
        Monopatin nuevo = new Monopatin(modelo);
        monopatinRepository.save(nuevo);
        MonopatinDto comun = new MonopatinDto(nuevo);
        return comun;
    }

    @Transactional
    public void borrar(Long id) {
        monopatinRepository.deleteById(id);
    }

    @Transactional
    public MonopatinDto getMonopatin(Long id) {
        MonopatinDto mDto = new MonopatinDto(monopatinRepository.getById(id));
        return mDto;
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

    public List<MonopatinDtoConPausa> getMonopatinesPorKmConPausa() {
        List<Object[]> resultados = monopatinRepository.getPorKmConPausa();
        List<MonopatinDtoConPausa> listaDto = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Long idMonopatin = (Long) resultado[0];
            String modelo = (String) resultado[1];
            int kmAcumulados = (int) resultado[2];
            Double tiempoPausado = (Double) resultado[3];
            System.out.println("id:" + idMonopatin + ", " + modelo + "," + kmAcumulados + "," + tiempoPausado);

            MonopatinDtoConPausa dto = new MonopatinDtoConPausa(idMonopatin, modelo, kmAcumulados, tiempoPausado);
            System.out.println(dto);
            listaDto.add(dto);
        }

        return listaDto;
    }
}

