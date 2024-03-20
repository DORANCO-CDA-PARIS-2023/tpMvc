package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/index")
    public String showLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }
}
