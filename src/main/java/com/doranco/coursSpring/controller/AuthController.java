package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, HttpSession session) {
        @SuppressWarnings("unchecked") // vs code fix
        List<User> users = (List<User>) session.getAttribute("users");
        if (users != null) {
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    session.setAttribute("currentUser", user);
                    return "redirect:/article";
                }
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String firstName, String lastName, String email, HttpSession session) {
        User newUser = new User(lastName, firstName, email);

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) session.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(newUser);

        session.setAttribute("users", users);

        return "redirect:/login";
    }
}
