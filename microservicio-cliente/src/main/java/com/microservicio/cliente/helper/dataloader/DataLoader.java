package com.microservicio.cliente.helper.dataloader;

import com.microservicio.cliente.helper.cargarCsv.CargarCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CargarCsv cargarCsv;

    @Autowired
    public DataLoader(CargarCsv cargarCsv) {
        this.cargarCsv = cargarCsv;
    }

    @Override
    public void run(String... args) throws Exception {
        cargarCsv.cargarDatosCuentasDesdeCSV();
        cargarCsv.cargarDatosUsuariosDesdeCSV();
    }
}