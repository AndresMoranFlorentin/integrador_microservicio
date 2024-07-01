package com.microservicio.monopatin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Microservicio-monopatin API", version = "1.0", description = "Documentacion Microservicio-monopatin API v1.0"))
public class MicroservicioMonopatinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioMonopatinApplication.class, args);
	}

}
