package com.example.csgoskinsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CsgoskinsbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsgoskinsbackendApplication.class, args);
	}

}
