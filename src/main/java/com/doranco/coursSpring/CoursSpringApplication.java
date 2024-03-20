package com.doranco.coursSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class CoursSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringApplication.class, args);
	}
}
