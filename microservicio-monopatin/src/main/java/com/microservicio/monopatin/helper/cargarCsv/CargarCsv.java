package com.microservicio.monopatin.helper.cargarCsv;


import com.microservicio.monopatin.model.Monopatin;
import com.microservicio.monopatin.model.Tarifa;
import com.microservicio.monopatin.model.Viaje;
import com.microservicio.monopatin.repository.MonopatinRepository;
import com.microservicio.monopatin.repository.TarifaRepository;
import com.microservicio.monopatin.repository.ViajeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CargarCsv {
    @Autowired
    private MonopatinRepository repoMono;
    @Autowired
    private TarifaRepository repoTarifa;
    @Autowired
    private ViajeRepository repoViaje;
    public void cargarDatosMonopatinDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("microservicio-monopatin/src/main/java/com/microservicio/monopatin/helper/csv/monopatines.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Monopatin mono = new Monopatin();
                mono.setIdMonopatin(Long.valueOf(csvRecord.get("idMonopatin")));
                mono.setModelo(String.valueOf(csvRecord.get("modelo")));
                mono.setEstado(String.valueOf(csvRecord.get("estado")));
                mono.setKmAcumulados(Integer.valueOf(csvRecord.get("kmAcumulados")));
                mono.setLongitud(Double.valueOf(csvRecord.get("longitud")));
                mono.setLatitud(Double.valueOf(csvRecord.get("latitud")));

                repoMono.save(mono);
            }
        }
    }
        public void cargarDatosTarifaDesdeCSV() throws IOException {
            File archivoCSV = ResourceUtils.getFile("microservicio-monopatin/src/main/java/com/microservicio/monopatin/helper/csv/tarifas.csv");

            try (FileReader reader = new FileReader(archivoCSV);
                 CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

                for (CSVRecord csvRecord : csvParser) {
                    Tarifa tarifa = new Tarifa();
                    tarifa.setIdTarifa(Long.valueOf(csvRecord.get("id_tarifa")));
                    tarifa.setPrecio(Double.valueOf(csvRecord.get("precio")));
                    repoTarifa.save(tarifa);
                }
            }
    }
    public void cargarDatosViajeDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("microservicio-monopatin/src/main/java/com/microservicio/monopatin/helper/csv/viajes.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Viaje viaje = new Viaje();
                viaje.setIdViaje(Long.valueOf(csvRecord.get("idViaje")));
                viaje.setInicio(LocalDateTime.parse(csvRecord.get("inicio")));
                viaje.setFin(LocalDateTime.parse(csvRecord.get("fin")));
                viaje.setIdMonopatin(Long.valueOf(csvRecord.get("idMonopatin")));
                viaje.setIdCuenta(Long.valueOf(csvRecord.get("idCuenta")));
                viaje.setIdUsuario(Long.valueOf(csvRecord.get("idUsuario")));
                viaje.setCosto(Double.valueOf(csvRecord.get("costo")));
                viaje.setConPausa(Boolean.parseBoolean(csvRecord.get("conPausa")));

                // Manejar las pausas
                if (viaje.getConPausa()) {
                    viaje.setInicioPausa(LocalDateTime.parse(csvRecord.get("inicioPausa")));
                    viaje.setFinPausa(LocalDateTime.parse(csvRecord.get("finPausa")));
                    viaje.setTiempoPausado(Double.valueOf(csvRecord.get("tiempoPausado")));
                }

                // Crear y asignar la Tarifa
                Tarifa tarifa = new Tarifa();
                tarifa.setIdTarifa(Long.valueOf(csvRecord.get("tarifa.id_tarifa")));
                tarifa.setPrecio(Double.valueOf(csvRecord.get("tarifa.precio")));
                //a la tarifa dejarle el viaje
                tarifa.addViaje(viaje);
                //guardar en el viaje la tarifa
                viaje.setTarifa(tarifa);

                // Asignar tarifa_extra
                viaje.setTarifaExtra(Double.valueOf(csvRecord.get("tarifaExtra")));

                // Guardar el viaje en la base de datos
                repoViaje.save(viaje);
            }
        }
    }
}
