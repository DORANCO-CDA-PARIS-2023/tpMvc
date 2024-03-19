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
    @GetMapping("/ajout")
    public String ajout(Model model){
        model.addAttribute("article", new Article());
        return "redirect:/article";
    }

    @PostMapping("/article")
    public String addArticle(@ModelAttribute Article article) {

        articleService.addArticle(article);
        return "ajout";
    }



    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "redirect:/article";
    }


    public String deteteArticle() {
        return "index";
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}