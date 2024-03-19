package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        this.articleService.addArticle(article);
        System.out.println(article.toString());
        return "index";
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model) throws NotFoundException {
        Article article = articleService.getArticles()
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseGet(null);

        if (article == null) {
            throw new NotFoundException();
        }

        model.addAttribute("article", article);
        return "article";
    }


    @DeleteMapping("/article/{id}")
    public String deteteArticle(@PathVariable int id) {
        return "index";
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}