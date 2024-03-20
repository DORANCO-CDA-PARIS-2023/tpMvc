package com.doranco.coursSpring.controller;

import com.doranco.coursSpring.enums.UserRoles;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.model.service.ArticleService;
import com.doranco.coursSpring.model.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ArticleController {

	private final ArticleService articleService;

	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@GetMapping("/")
	public String listArticles(Model model, HttpSession session) {
		var articles = this.articleService.getArticles();
		if (session.getAttribute("login") != null) {
			model.addAttribute("login", session.getAttribute("login"));
			model.addAttribute("articles", articles);
        }
		return "index";
	}

	@PostMapping("/create")
	public RedirectView addArticle(@ModelAttribute Article article, HttpSession session, Model model) {
		if (session.getAttribute("login") != null) {
			this.articleService.addArticle(article);
		} else {
			model.addAttribute("error", "You must be logged in to create an article");
			return new RedirectView("/login");
		}
		return new RedirectView("/");
	}

	@GetMapping("/article/{id}")
	public String getArticle(@PathVariable int id, Model model, HttpSession session) {
		if (session.getAttribute("login") != null) {
			Article article = this.articleService.getArticle(id);
			model.addAttribute("article", article);
		} else {
			model.addAttribute("error", "You must be logged in to see an article");
			return "/login";
		}

		return "article";
	}


	@GetMapping("/article/{id}/delete")
	public RedirectView deteteArticle(@PathVariable int id, HttpSession session, Model model) {
		if (session.getAttribute("login") != null) {
			try {
				Article article = this.articleService.getArticle(id);
				User user = UserService.getUerByEMail(session.getAttribute("login").toString());
				if (user.getId() == article.getAuthor().getId() || user.getRole().equals(UserRoles.ADMIN)) {
					this.articleService.deleteArticle(id);
				} else {
					model.addAttribute("error", "You must be an admin to delete an article");
					return new RedirectView("/");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			model.addAttribute("error", "You must be logged in to delete an article");
			return new RedirectView("/login");
		}
		return new RedirectView("/");
	}

	@PutMapping("/{id}")
	public String updateArticle() {
		return "index";
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends Exception {
}