package com.doranco.coursSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/login")
    public String getUserLogin() {
        return "login";
    }

    @GetMapping("/user/register")
    public String getuserRegister() {
        return "register";
    }

}
