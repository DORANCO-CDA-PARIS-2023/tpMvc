package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.service.ArticleService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView addArticle(@RequestParam("title") String title,
            @RequestParam("content") String content,
            HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            return new RedirectView("/login");
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(currentUser);

        articleService.addArticle(article);

        return new RedirectView("/article");
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
class NotFoundException extends Exception {
}