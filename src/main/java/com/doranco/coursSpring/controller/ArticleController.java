package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.service.ArticleService;
import com.doranco.coursSpring.repository.ArticleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class ArticleController {


    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository)
    {

        this.articleRepository = articleRepository;
    }


    @GetMapping("/")
    public RedirectView home() {
        return new RedirectView("/article");
    }

    @GetMapping("/article")
    public String listArticles(Model model, HttpSession session)
    {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        var articles = this.articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }

    @PostMapping("/article")
    public RedirectView addArticle(@ModelAttribute Article article, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new RedirectView("/login");
        }
        articleRepository.save(article);
        return new RedirectView("/article");
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            model.addAttribute("article", article);
            return "article";
        } else {
            return null;
        }
    }


    @GetMapping("/article/{id}/delete")
    public RedirectView deteteArticle(@PathVariable int id) {
        articleRepository.deleteById(id);
        return new RedirectView("/article");
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {}