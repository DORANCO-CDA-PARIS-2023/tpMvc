package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String user(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user";
    }
}
