package com.microservicio.administracion.helper.cargarCsv;

import com.microservicio.administracion.entity.Ticket;
import com.microservicio.administracion.repository.AdminRepository;
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
    private AdminRepository adminRepository;
    public void cargar() throws IOException {
        File archivoCSV = ResourceUtils.getFile("microservicio-administracion/src/main/java/com/microservicio/administracion/helper/Csv/ticket.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Ticket ticket = new Ticket();
                ticket.setId(Long.valueOf(csvRecord.get("id")));
                ticket.setFecha(LocalDateTime.parse(csvRecord.get("fecha")));
                ticket.setIdCuenta(Long.valueOf(csvRecord.get("idCuenta")));
                ticket.setId_usuario(Long.valueOf(csvRecord.get("id_usuario")));
                ticket.setMonto(Double.parseDouble(csvRecord.get("monto")));

                adminRepository.save(ticket); // Guarda el ticket en la base de datos
            }
        }
    }
}
