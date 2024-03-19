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
		articles.add(new Article(1, "titre_1", "blabla", new User("Bob", "Bobby", "user_1", "1234", "bob@bobby.com")));
		articles.add(new Article(2, "titre_2", "blabla", new User("Bob", "Bobby", "user_2", "1234", "bob@bobby.com")));
		articles.add(new Article(3, "titre_3", "blabla", new User("Bob", "Bobby", "user_3", "1234", "bob@bobby.com")));
		articles.add(new Article(4, "titre_4", "blabla", new User("Bob", "Bobby", "user_4", "1234", "bob@bobby.com")));
	}

	private int newID() {
		int id = 0;
		for (var article : articles) {
			if (article.getId() > id)
				id = article.getId();
		}
		return id + 1;
	}

	public void addArticle(Article article) {
		if (article.getId() == 0)
			article.setId(newID());
		this.articles.add(article);
	}

	public List<Article> getArticles() {
		return this.articles;
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
