
package com.doranco.coursSpring;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.Auth.AuthForm;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.AuthService;
import com.doranco.coursSpring.repository.ArticleRepository;
import com.doranco.coursSpring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoursSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AuthService authService, UserRepository userRepository, ArticleRepository articleRepository) {
		return args -> {
			authService.register(new AuthForm("toto", "toto", "toto@toto.fr", "toto", "toto"));
			User u = userRepository.findByEmail("toto@toto.fr");
			Article article1 = new Article("titre 1", "azertyuio", u);
			Article article2 = new Article("titre 2", "azertyuio", u);
			articleRepository.save(article1);
			articleRepository.save(article2);
		};
	}
}
