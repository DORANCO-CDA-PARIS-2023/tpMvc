package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.Auth.AuthForm;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.AuthService;
import com.doranco.coursSpring.model.service.UserService;
import com.doranco.coursSpring.model.service.exception.EmptyFormException;
import com.doranco.coursSpring.model.service.exception.MismatchPasswordException;
import com.doranco.coursSpring.model.service.exception.NotFoundUserException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }


    @GetMapping("/login")
    public String login()
    {
        return "auth/login";
    }

    @PostMapping("/login")
    public String toLogin(@ModelAttribute AuthForm loginForm, Model model, HttpSession session)
    {
        if (session.getAttribute("user") != null) {
            model.addAttribute("error", "Already connected !!!");
            return "auth/login";
        }
        try {
            User user = authService.login(loginForm);
            session.setAttribute("user", user);
            model.addAttribute("success", "Connexion validée ! ");
        } catch (EmptyFormException e) {
            model.addAttribute("error", "Les champs sont obligatoires !");
        } catch (NotFoundUserException e) {
            model.addAttribute("error", "Identifiants invalides");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register()
    {
        return "auth/register";
    }

    @PostMapping("/register")
    public String toRegister(@ModelAttribute AuthForm form, Model model, UserService userService)
    {
        try {
            authService.register(form);
            model.addAttribute("success", "Inscription terminée !");
        } catch (MismatchPasswordException e) {
            model.addAttribute("error", "Les mots de passe sont différents");
        } catch (EmptyFormException e) {
            model.addAttribute("error", "Les champs sont obligatoires !");
        }

        return "auth/register";
    }
}
