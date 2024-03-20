package com.doranco.coursSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SessionController {
	
    private final UserService userService;

    public SessionController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/login")
    @ResponseBody
    public String session(HttpSession session)
    {
        if (session.getAttribute("login") == null) {
            session.setAttribute("login", "Robert");
            return "Session VIDE";
        }
        return "SESSION OK : " + session.getAttribute("login");
    }
  
    @GetMapping("/register")
    public String getRegister(@ModelAttribute User user) {
        userService.addUser(user);
        return "register";
    }
    
    @PostMapping("/register")
    public RedirectView register(@ModelAttribute User user) {
        userService.addUser(user);
        return new RedirectView("/login");
    }
}
