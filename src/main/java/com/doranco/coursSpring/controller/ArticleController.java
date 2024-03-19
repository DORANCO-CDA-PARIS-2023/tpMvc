package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.doranco.coursSpring.model.entity.Article;
import org.springframework.web.bind.annotation.RequestParam;
import com.doranco.coursSpring.model.entity.User;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public String listArticles(Model model) {
        var articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("article", new Article());
        return "index";
    }

    @PostMapping("/article")
    public String addArticle(Article article,
            @RequestParam String authorFirstName, // to add all the author's params
            @RequestParam String authorLastName,
            @RequestParam String authorEmail,
            Model model) {
        User author = new User(authorLastName, authorFirstName, authorEmail);
        article.setAuthor(author); // we associate the author with the article
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
class NotFoundException extends Exception {
}