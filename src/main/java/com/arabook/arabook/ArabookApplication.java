package com.arabook.arabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ArabookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArabookApplication.class, args);
	}
}
