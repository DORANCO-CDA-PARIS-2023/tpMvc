package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final List<Article> articles;

    public ArticleService() {
        articles = new ArrayList<>();
        articles.add(
                new Article(
                        1,
                        "titre1",
                        "blabla",
                        new User("Bob", "Bobby", "bob@bobby.com")
                )
        );
    }

    public void addArticle(Article article) {
    	articles.add(article);
    }

    public List<Article> getAllArticles() {
        return this.articles;
    }

    public Article getArticleById(int id) {
    	for(Article a : articles) {
    		if(a.getId() == id) {
    			return a;
    		}
    	}
    	return null; }

    public void deleteArticle(int id) {
    	articles.remove(this.getArticleById(id));
    }
}
