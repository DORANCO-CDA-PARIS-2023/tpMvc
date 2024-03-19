package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.exception.NotFoundException;
import com.doranco.coursSpring.exception.UnauthorizedException;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public RedirectView register() {
        return new RedirectView("/login");
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute User user, HttpSession session) throws UnauthorizedException, NotFoundException {
        if (session.getAttribute("login") != null) {
            return new RedirectView("/article");
        }

        if (user.getEmail() == null || user.getPassword() == null) {
            throw new UnauthorizedException();
        }

        User userByEmail = this.userService.getUserByEmail(user.getEmail());
        if (userByEmail == null) {
            throw new NotFoundException();
        }

        if (!user.getPassword().equals(userByEmail.getPassword())) {
            throw new UnauthorizedException();
        }

        session.setAttribute("login", userByEmail);
        return new RedirectView("/article");
    }

    @GetMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/login");
    }

}
