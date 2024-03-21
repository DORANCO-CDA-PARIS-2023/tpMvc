package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticle(Integer id) {
        return articleRepository.findById(id);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}
