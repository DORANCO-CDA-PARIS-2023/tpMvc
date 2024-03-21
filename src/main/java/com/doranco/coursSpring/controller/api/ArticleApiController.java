package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Article> getById(@PathVariable int id)
    {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/article")
    public ResponseEntity<Article> add(@RequestBody Article article) {
        Article saveArticle = articleRepository.save(article);
        return new ResponseEntity<>(saveArticle, HttpStatus.CREATED);
    }


    @PutMapping("/api/article/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article updatedArticle) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article currentArticle = optionalArticle.get();
            currentArticle.setTitle(updatedArticle.getTitle());
            currentArticle.setContent(updatedArticle.getContent());
            articleRepository.save(currentArticle);
            return new ResponseEntity<>(currentArticle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            articleRepository.deleteById(id);
            return new ResponseEntity<>("Article correctement supprimer", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erreur article non supprimer", HttpStatus.NOT_FOUND);
        }
    }
}
