package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }


    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/article");
    }

    @GetMapping("/article")
    public String listArticles(Model model) {
        var articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @PostMapping("/article")
    public String addArticle() {
        return "index";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article";
    }


    @GetMapping("/article/{id}/delete")
    public RedirectView deteteArticle(@PathVariable int id) {
        articleService.deleteArticle(id);
        return new RedirectView("/article");
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}