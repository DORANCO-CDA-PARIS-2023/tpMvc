package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.exception.NotFoundException;
import com.doranco.coursSpring.exception.UnauthorizedException;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.ArticleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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
    public String listArticles(Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login";
        }

        List<Article> articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        return "article/allArtciles";
    }

    @PostMapping("/article")
    public RedirectView addArticle(@ModelAttribute Article article, HttpSession session) throws UnauthorizedException {
        Object loginAttribut = session.getAttribute("login");
        if (loginAttribut == null) {
            throw new UnauthorizedException();
        }

        User user = (User) session.getAttribute("login");
        article.setUser(user);

        this.articleService.addArticle(article);
        return new RedirectView("/article");
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable int id, Model model, HttpSession session) throws NotFoundException {
        Optional<Article> article = articleService.getArticle(id);

        if (article.isEmpty()) {
            throw new NotFoundException();
        }

        model.addAttribute("article", article.get());
        return "article/article";
    }

    @GetMapping("/article/add")
    public String addArticle(Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login";
        }

        model.addAttribute("login", session.getAttribute("login"));

        return "article/addArticle";
    }


    @GetMapping("/article/{id}/delete")
    public RedirectView deleteArticle(@PathVariable int id) throws NotFoundException {
        this.articleService.deleteArticle(id);

        return new RedirectView("/article");
    }

    @PostMapping("/article/{id}/modify")
    public RedirectView modifyArticle(@ModelAttribute Article article) {
        Optional<Article> serviceArticle = this.articleService.getArticle(article.getId());
        if (serviceArticle.isPresent()) {
            serviceArticle.get().setTitle(article.getTitle());
            serviceArticle.get().setContent(article.getContent());
        }

        return new RedirectView("/article/" + article.getId());
    }

    @GetMapping("/article/{id}/modify")
    public String modifyArticle(@PathVariable int id, Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login";
        }

        model.addAttribute("article", articleService.getArticle(id).isEmpty() ? null : articleService.getArticle(id).get() );

        return "article/modifyArticle";
    }

}