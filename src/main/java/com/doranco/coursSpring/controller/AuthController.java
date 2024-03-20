package com.doranco.coursSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/register")
	public String register(User user) {
		var reg = authService.register(user);
		if (reg != null) {
			return "redirect:/login";
		}
		return "auth/register";
	} 
	
	@PostMapping("/login")
	public String login(String email, String password, HttpSession session) {
		var connect = authService.login(email, password);
		if (connect != null) {
			if (session.getAttribute("login") == null) {
				session.setAttribute("login", connect.getEmail());
				return "redirect:/users/{connect.id}";
			}
			return "auth/login";
		}
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public RedirectView logout(HttpSession session) {
		session.removeAttribute("login");
		return new RedirectView("/login");
	}

}
