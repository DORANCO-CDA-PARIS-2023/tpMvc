package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.exception.AlreadyRegisteredException;
import com.doranco.coursSpring.model.exception.IncompleteFormException;
import com.doranco.coursSpring.model.exception.InvalidLoginException;
import com.doranco.coursSpring.model.exception.MissMatchPasswordException;
import com.doranco.coursSpring.model.form.LoginForm;
import com.doranco.coursSpring.model.form.RegisterForm;
import com.doranco.coursSpring.model.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm, Model model, HttpSession session) {
        if (session.getAttribute("login") != null) {
            return "redirect:/";
        }

        try {
            this.authService.register(registerForm);
            return "redirect:/login";
        } catch (IncompleteFormException | AlreadyRegisteredException | MissMatchPasswordException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "auth/register";
    }

    @PostMapping("/login")
    public Object login(@ModelAttribute LoginForm loginForm, Model model, HttpSession session) {
        if (session.getAttribute("login") != null) {
            return "redirect:/";
        }

        try {
            User login = this.authService.login(loginForm);
            session.setAttribute("login",login);
            return "redirect:/";
        } catch (IncompleteFormException | InvalidLoginException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "auth/login";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.removeAttribute("login");
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getUserLogin(Model model, HttpSession session) {
        model.addAttribute("login", session.getAttribute("login"));
        return "auth/login";
    }

    @GetMapping("/register")
    public String getuserRegister(Model model, HttpSession session) {
        model.addAttribute("login", session.getAttribute("login"));
        return "auth/register";
    }

}
