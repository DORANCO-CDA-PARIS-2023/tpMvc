package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.doranco.coursSpring.model.entity.Article;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }


    @GetMapping("/article")
    public String listArticles(Model model) {
        var articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        return "index";
    }

    @PostMapping("/article")
    public String addArticle(@ModelAttribute Article article) {
        articleService.addArticle(article);
        return "redirect:/article";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        Article articleId = this.articleService.getArticlesById(id);
        model.addAttribute("articleId", articleId);
        return "index";
    }


    public String deleteArticle(@PathVariable int id) {
        this.articleService.deleteArticle(id);
        return "index";
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}