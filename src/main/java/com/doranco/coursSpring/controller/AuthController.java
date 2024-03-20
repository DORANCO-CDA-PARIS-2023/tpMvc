package com.doranco.coursSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String register(User user, Model model) {
		var reg = authService.register(user);
		if (reg != null) {
			model.addAttribute("success", "Inscription validée !");
			return "redirect:/login";
		}
		return "auth/register";
	}

	@GetMapping("/register")
	public String register() {
		return "auth/register";
	}

	@PostMapping("/login")
	public String login(String email, String password, HttpSession session, Model model) {
		var connect = authService.login(email, password);
		if (connect != null) {
			if (session.getAttribute("login") == null) {
				session.setAttribute("login", connect.getEmail());
				model.addAttribute("success", "Connexion validé ! ");
				System.out.println("Connexion validé ! " + connect.getEmail());
				System.out.println("Connexion validé ! " + session.getAttribute("login"));
				return "redirect:/users/"+ connect.getId();
			} else {
				model.addAttribute("error", "Vous êtes déjà connecté !");
				return "auth/login";
			}
		}
		return "auth/login";
	}

	@GetMapping("/login")
	public String login() {

		return "auth/login";
	}

	@GetMapping("/logout")
	public RedirectView logout(HttpSession session, Model model) {
		session.removeAttribute("login");
		model.addAttribute("success", "Vous êtes déconnecté !");
		return new RedirectView("/login");
	}

}
