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

    @GetMapping("/article")
    public String listArticles(Model model, HttpSession session) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login?redirect=/article";
        }

        List<Article> articles = this.articleService.getArticles();
        model.addAttribute("articles", articles);
        return "article/allArtciles";
    }

    @PostMapping("/article")
    public RedirectView addArticle(@ModelAttribute Article article,
                                   HttpSession session,
                                   @RequestParam(
                                           value = "redirect",
                                           defaultValue = "/my-articles",
                                           required = false
                                   ) String redirect) throws UnauthorizedException {

        Object loginAttribut = session.getAttribute("login");
        if (loginAttribut == null) {
            throw new UnauthorizedException();
        }

        User user = (User) session.getAttribute("login");
        article.setUser(user);

        this.articleService.addArticle(article);
        return new RedirectView(redirect);
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
    public String addArticle(Model model,
                             HttpSession session,
                             @RequestParam(value = "redirect", required = false) String redirect) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login?redirect=/article/add";
        }

        model.addAttribute("login", session.getAttribute("login"));
        model.addAttribute("redirect", redirect);

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
            return "redirect:/login?redirect=/article/" + id + "/modify";
        }

        model.addAttribute("article", articleService.getArticle(id).isEmpty() ? null : articleService.getArticle(id).get() );

        return "article/modifyArticle";
    }

    @GetMapping("/my-articles")
    public String myArticles(HttpSession session, Model model) {
        if (session.getAttribute("login") == null) {
            return "redirect:/login?redirect=/my-articles";
        }

        User user = (User) session.getAttribute("login");
        List<Article> articlesByUser = this.articleService.getArticlesByUser(user);
        model.addAttribute("articles", articlesByUser);
        return "user/my-articles";
    }

}