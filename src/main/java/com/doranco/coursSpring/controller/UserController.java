package com.doranco.coursSpring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/login")
    public String getUserLogin(Model model, HttpSession session) {
        model.addAttribute("login", session.getAttribute("login"));
        return "login";
    }

    @GetMapping("/user/register")
    public String getuserRegister(Model model, HttpSession session) {
        model.addAttribute("login", session.getAttribute("login"));
        return "register";
    }

}
