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
        System.out.println(article);
articles.add(article);

    }

    public List<Article> getArticles() {
        return this.articles;
    }

    public Article getArticle(int id) {
        for(Article article : articles){
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void deleteArticle(int id) {
       Article article = getArticle(id);
      if (article != null)
          articles.remove(article);
    }
}
