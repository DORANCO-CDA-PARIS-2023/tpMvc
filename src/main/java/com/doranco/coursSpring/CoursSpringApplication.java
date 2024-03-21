package com.doranco.coursSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.doranco.coursSpring.repository")
public class CoursSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringApplication.class, args);
	}
// 	@Bean
// 	CommandLineRunner start(AuthService authService) {
// 		return args -> {
// //			authService.register();
// 		};
// 	}
}
