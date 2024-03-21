package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleReposirory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

	private final ArticleReposirory articleRepository;

	public ArticleService(ArticleReposirory articleRepository) {
		this.articleRepository = articleRepository;
	}

	public void addArticle(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getArticles() {
		return articleRepository.findAll();
	}

	public Article getArticle(int id) {
		return articleRepository.findArticleById(id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteById(id);
	}
}
