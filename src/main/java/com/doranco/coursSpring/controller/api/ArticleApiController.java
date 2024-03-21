package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleReposirory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

    private final ArticleReposirory articleRepository;

    public ArticleApiController(ArticleReposirory articleRepository)
    {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/article")
    public List<Article> getAll()
    {
        return articleRepository.findAll();
    }

    @GetMapping("/api/article/{id}")
    public Article getById(@PathVariable int id)
    {
    	Article article = articleRepository.findById(id).get();
        return article;
    }

    @PostMapping("/api/article")
    public Article add(@RequestBody Article article)
    {
    	var art = articleRepository.save(article);
//    	System.out.println("========== "+ art);
        return art;
    }


    @PutMapping("/api/article/{id}")
    public Article update(@PathVariable int id, @RequestBody Article article)
    {
    	
        return null;
    }

    @DeleteMapping("/api/article/{id}")
    public String deleteById(@PathVariable int id) {
        return "";
    }
}