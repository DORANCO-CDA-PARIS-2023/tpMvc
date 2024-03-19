package com.doranco.coursSpring.controller;
import com.doranco.coursSpring.model.service.ArticleService;
import com.doranco.coursSpring.model.entity.Article;
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

    @GetMapping("/article")
    public String getIndex(Model model) {
        model.addAttribute("articles", articleService.getArticles());
        return "index";
    }

    @PostMapping("/ajout")
    public String addArticle(Article article) {
        articleService.addArticle(article);
        return "redirect:/article";
    }

    @GetMapping("/article/{id}")
    public String getArticle() {
        return "index";
    }


    public String deteteArticle() {
        return "index";
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}
