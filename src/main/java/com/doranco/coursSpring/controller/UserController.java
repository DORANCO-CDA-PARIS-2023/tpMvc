package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "utilisateur";
    }

    @PostMapping("/create")
    public RedirectView addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return new RedirectView("/users");
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "userProfile";
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new RedirectView("/users");
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id) {
        return "redirect:/users";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException() {
        return "error/404"; 
    }
}
