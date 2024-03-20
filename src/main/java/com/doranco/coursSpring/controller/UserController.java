package com.doranco.coursSpring.controller;


import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUser(Model model){
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUser (@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);


        return "user";
    }

    @GetMapping("/user")
    public String user(Model model){
        model.addAttribute("user", new User());
        return "user";
    }

    @GetMapping("/adduser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "adduser";
    }

    @PostMapping("/adduser")
    public RedirectView addUser(@ModelAttribute User user) {

        userService.addUser(user);
        return new RedirectView("/users");
    }

    @GetMapping("/delete/user/{id}")
    public RedirectView deleteUser(@PathVariable int id){
        userService.deleteUser(id);
       return new RedirectView("/index");
    }
}
