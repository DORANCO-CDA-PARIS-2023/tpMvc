package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        articles.add(
                new Article(
                        2,
                        "titre2",
                        "blabla2",
                        new User("Bob", "Bobby", "bob@bobby.com")
                )
        );
        articles.add(
                new Article(
                        3,
                        "titre3",
                        "blabla3",
                        new User("Bob", "Bobby", "bob@bobby.com")
                )
        );
    }
    
    private int newID(){
    	int id = 0;
    	for (var article: articles) {
    		if (article.getId()> id)
    			id = article.getId();
    		}
    		return id + 1;
    }

    public void addArticle(Article article) {
    	if (article.getId() == 0) {
    		article.setId(newID());    	}
        	articles.add(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(int id) {
    	for (Article article : articles) {
    		if (article.getId() == id) {
    				return article;
    			}
    		}
    	return null;
    }

    public void deleteArticle(int id) {
    	Article article = getArticle(id);
        articles.remove(article);
    }
}
