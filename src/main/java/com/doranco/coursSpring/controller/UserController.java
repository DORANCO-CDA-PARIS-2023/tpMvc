package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    private final ArticleService articleService;

    public UserController(ArticleService articleService) {
        this.articleService = articleService;
    }

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

    @GetMapping("/user/article/add")
    public Object addArticle(Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return new RedirectView("/user/login");
        }

        model.addAttribute("login", session.getAttribute("login"));

        return "article/addArticle";
    }

    @GetMapping("/user/article/{id}/modify")
    public Object modifyArticle(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return new RedirectView("/user/login");
        }

        model.addAttribute("login", session.getAttribute("login"));
        model.addAttribute("article", articleService.getArticle(id).isEmpty() ? null : articleService.getArticle(id).get() );

        return "article/modifyArticle";
    }

}
