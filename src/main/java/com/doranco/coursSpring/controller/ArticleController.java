package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.exception.NotFoundException;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }


    @GetMapping("/article")
    public String listArticles(Model model, HttpSession session) {
        List<Article> articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("login", session.getAttribute("login"));
        return "index";
    }

    @PostMapping("/article")
    public RedirectView addArticle(@ModelAttribute Article article) {
        this.articleService.addArticle(article);
        return new RedirectView("/article");
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model, HttpSession session) throws NotFoundException {
        Article article = articleService.getArticle(id);

        if (article == null) {
            throw new NotFoundException();
        }

        model.addAttribute("article", article);
        model.addAttribute("login", session.getAttribute("login"));
        return "article";
    }


    @GetMapping("/article/{id}/delete")
    public RedirectView deleteArticle(@PathVariable int id) throws NotFoundException {
        Article deletedArticle = this.articleService.deleteArticle(id);
        if (deletedArticle == null) {
            throw new NotFoundException();
        }

        return new RedirectView("/article");
    }

}