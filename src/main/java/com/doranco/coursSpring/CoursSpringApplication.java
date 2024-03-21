package com.doranco.coursSpring;

import com.doranco.coursSpring.model.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoursSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner start(AuthService authService) {
		return args -> {
//			authService.register();
		};
	}*/
}
