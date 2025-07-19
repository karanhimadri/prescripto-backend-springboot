package com.example.prescripto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Prescripto API",
				version = "1.0",
				description = "API documentation for the Prescripto Healthcare system"
		)
)
@SpringBootApplication
public class PrescriptoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrescriptoApplication.class, args);
	}
}





