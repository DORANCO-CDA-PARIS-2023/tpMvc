package com.doranco.coursSpring.model.service;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private static final List<Article> articles = new ArrayList<>();;

    public ArticleService() {
       // articles =
        var author = new User("Bob", "Bobby", "bob@bobby.com", "1234");
        articles.add(
                new Article(
                        1,
                        "titre1",
                        "blabla",
                        author
                )
        );
        articles.add(
                new Article(
                        2,
                        "titre1",
                        "blabla",
                        author
                )
        );
        articles.add(
                new Article(
                        3,
                        "titre1",
                        "blabla",
                        author
                )
        );
        articles.add(
                new Article(
                        4,
                        "titre1",
                        "blabla",
                        author
                )
        );
    }

    private int newID()
    {
        int id = 0;
        for (var article: articles)
        {
            if (article.getId() > id)
                id = article.getId();
        }
        return id + 1;
    }

    public void addArticle(Article article)
    {
        if (article.getId() == 0) {
            article.setId(newID());
        }
        articles.add(article);
    }

    public List<Article> getArticles()
    {
        return articles;
    }

    public Article getArticle(int id)
    {
        for (Article article : articles)
        {
            if (article.getId() == id)
                return article;
        }
        return null;
    }

    public void deleteArticle(int id)
    {
        Article article = getArticle(id);
        articles.remove(article);
    }
    
    public List<Article> getArticlesByAuthor(User user){
    	List<Article> result = new ArrayList();
    	for(Article a : articles) {
    		if(a.getAuthor().equals(user)) {
    			result.add(a);
    		}
    	}
    	return result;
    }
}
