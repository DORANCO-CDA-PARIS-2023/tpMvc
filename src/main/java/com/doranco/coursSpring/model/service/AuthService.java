package com.doranco.coursSpring.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.doranco.coursSpring.model.entity.User;

@Service
public class AuthService {

	private final UserService userService;

	public AuthService(UserService userService) {
		this.userService = userService;
	}

	public boolean isLogged(Boolean isLogged) {
		return isLogged;
	}

	public RedirectView register(User user) {
		this.userService.addUser(user);
		return new RedirectView("/users");
	}

	public User login(String email, String password) {
		User user = userService.getUerByEMail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				isLogged(true);
				return user;
			}
		} else {
			isLogged(false);
			return null;
		}
		return null;
	}

	public boolean logout() {
		return true;
	}

}
