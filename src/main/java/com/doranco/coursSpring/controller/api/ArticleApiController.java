package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

    private final ArticleRepository articleRepository;

    public ArticleApiController(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/article")
    public List<Article> getAll()
    {
        return articleRepository.findAll();
    }

    @GetMapping("/api/article/{id}")
    public Article getById(int id)
    {
        return null;
    }

    @PostMapping("/api/article")
    public Article add(/* TODO */)
    {
        return null;
    }


    @PutMapping("/api/article/{id}")
    public Article update(/* TODO */)
    {
        return null;
    }

    @DeleteMapping("/api/article/{id}")
    public String deleteById(int id) {
        return "";
    }
}
