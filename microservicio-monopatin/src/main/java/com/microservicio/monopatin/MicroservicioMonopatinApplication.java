package com.microservicio.monopatin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MicroservicioMonopatinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioMonopatinApplication.class, args);
	}

}
