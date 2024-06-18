package com.microservicio.cliente.helper.cargarCsv;

import com.microservicio.cliente.entities.Cuenta;
import com.microservicio.cliente.entities.Usuario;
import com.microservicio.cliente.repositories.RepositoryCuenta;
import com.microservicio.cliente.repositories.RepositoryUsuario;
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
    private RepositoryCuenta repoCuenta;
    @Autowired
    private RepositoryUsuario repoUsuario;
    public void cargarDatosCuentasDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("microservicio-cliente/src/main/java/com/microservicio/cliente/helper/csv/cuentas.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId_cuenta(Long.valueOf(csvRecord.get("id_cuenta")));
                cuenta.setNombre_cuenta(csvRecord.get("nombre"));
                cuenta.setMonto(Double.parseDouble(csvRecord.get("monto")));
                cuenta.setHabilitado(true);
                cuenta.setFecha_de_alta(LocalDateTime.parse(csvRecord.get("fecha")));
                repoCuenta.save(cuenta); // Guarda la carrera en la base de datos
            }
        }
    }
    public void cargarDatosUsuariosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("microservicio-cliente/src/main/java/com/microservicio/cliente/helper/csv/usuarios.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Usuario user = new Usuario();
                user.setId_usuario(Long.valueOf(csvRecord.get("id_usuario")));
                user.setNombre(csvRecord.get("nombre"));
                user.setCelular(Long.valueOf(csvRecord.get("celular")));
                user.setEmail(csvRecord.get("email"));
                user.setApellido(csvRecord.get("apellido"));
                repoUsuario.save(user); // Guarda la carrera en la base de datos
            }
        }
    }
}
