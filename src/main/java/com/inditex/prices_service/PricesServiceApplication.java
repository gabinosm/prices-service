package com.inditex.prices_service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PricesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesServiceApplication.class, args);
	}

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("API de Precios")
						.version("1.0")
						.description("Documentación de la API de precios para productos, y marca incluidos en campañas"));
	}

}
