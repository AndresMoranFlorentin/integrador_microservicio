package com.microservicio.monopatin.service;

import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;
    private LocalDate fechaActualizar = LocalDate.of(2024, 06, 27).plusDays(10);
    private final Long ID = 1L;
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public String actualizarTarifa(Double nuevaTarifa) {
        // Busca la tarifa existente por algún criterio (por ejemplo, ID o nombre)
        Optional<Tarifa> tarifaExistente = tarifaRepository.findById(ID);
        Tarifa tarifaE = tarifaExistente.get();
        // Actualiza la tarifa solo si la fecha es posterior a la fecha actual
        if (fechaActualizar.equals(LocalDate.now())) {
            tarifaE.setPrecio(nuevaTarifa);
            tarifaRepository.save(tarifaE);
            return "se actualizo la tarifa en el dia hecho";
        } else if (fechaActualizar.isBefore(LocalDate.now())) {
            // Maneja el caso
             throw new IllegalArgumentException("La fecha no puede ser anterior a hoy ");
        } else {
            return "la fecha todavia no es la correcta";
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

