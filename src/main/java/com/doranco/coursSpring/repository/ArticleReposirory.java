package com.doranco.coursSpring.repository;

import java.util.ArrayList;

//import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doranco.coursSpring.model.entity.Article;

public interface ArticleReposirory extends JpaRepository<Article, Integer> {
	
//	public Article createArticle(Article article);
	
	
	public Article findArticleById(int id);
	
	public Article deleteById(int id);
	
//	ArrayList<Article> findByTitle(String title);
//	
//	ArrayList<Article> findByContent(String content);
//	
//	ArrayList<Article> findByAuthor(String author);
//	
//	ArrayList<Article> findByDateTime(String dateTime);
//	
//	ArrayList<Article> findByTitleOrContent(String title, String content);
//	
//	Article createArticle(String title, String content, String author);
//	
//	Article updateArticle(String title, String content, String author);
//	
//	Article deleteArticle(String title);
//	
//	Article findById(int id);
//	
//	ArrayList<Article> findAll();
	
	

}
