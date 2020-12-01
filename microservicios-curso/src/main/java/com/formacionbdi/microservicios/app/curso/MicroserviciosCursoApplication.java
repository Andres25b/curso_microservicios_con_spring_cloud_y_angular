package com.formacionbdi.microservicios.app.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursoApplication.class, args);
	}

}
