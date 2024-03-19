package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final List<Article> articles;
    private int idCount;

    public ArticleService() {
        this.idCount = 1;
        articles = new ArrayList<>();
        articles.add(
                new Article(
                        this.idCount++,
                        "Article de Bob",
                        "blabla",
                        new User("Bob", "Bobby", "bob@bobby.com")
                )
        );
    }

    public int addArticle(Article article) {
        article.setId(this.idCount++);
        this.articles.add(article);

        return article.getId();
    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public Article getArticle(int id) {return null; }

    public void deleteArticle(int id) {}
}
