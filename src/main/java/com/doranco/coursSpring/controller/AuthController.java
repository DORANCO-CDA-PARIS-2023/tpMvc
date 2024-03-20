package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        Optional<User> userOptional = userService.authenticate(loginUser.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getEmail().equals(loginUser.getEmail())) {
                return "Authentication successful!";
            }
        }
        return "Authentication failed!";
    }
}
