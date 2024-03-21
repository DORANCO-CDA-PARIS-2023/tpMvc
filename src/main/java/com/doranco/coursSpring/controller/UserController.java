package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.ArticleService;
import com.doranco.coursSpring.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ArticleService articleService;

    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

}
