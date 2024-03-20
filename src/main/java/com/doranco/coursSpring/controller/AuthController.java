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
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }


    @GetMapping("/login")
    public String login(HttpSession session)
    {
        if (session.getAttribute("user") != null) {
            return "redirect:/article";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String toLogin(@ModelAttribute AuthForm loginForm, Model model, HttpSession session)
    {
        try {
            User user = authService.login(loginForm);
            session.setAttribute("user", user);
            return "redirect:/article";
            //model.addAttribute("success", "Connexion validé ! ");
        } catch (EmptyFormException e) {
            model.addAttribute("error", "Les champs sont obligatoires !");
        } catch (NotFoundUserException e) {
            model.addAttribute("error", "Identifiants invalide");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(HttpSession session)
    {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String toRegister(@ModelAttribute AuthForm form, Model model, UserService userService)
    {
        try {
            authService.register(form);
            model.addAttribute("success", "Inscription terminé !");
        } catch (MismatchPasswordException e) {
            model.addAttribute("error", "Les mots de passes sont differents");
        } catch (EmptyFormException e) {
            model.addAttribute("error", "Les champs sont obligatoires !");
        }

        return "auth/register";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session)
    {
        session.removeAttribute("user");
        return new RedirectView("/");
    }
}
