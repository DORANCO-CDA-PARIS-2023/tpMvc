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

    public ArticleApiController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/article")
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Article> getById(@PathVariable("id") Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/article")
    public ResponseEntity<Article> add(@RequestBody Article article) {
        try {
            Article savedArticle = articleRepository.save(article);
            return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/api/article/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody Article article) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article existingArticle = optionalArticle.get();
            existingArticle.setTitle(article.getTitle());
            existingArticle.setContent(article.getContent());
            try {
                Article updatedArticle = articleRepository.save(existingArticle);
                return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            try {
                articleRepository.deleteById(id);
                return new ResponseEntity<>("Article deleted successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Article not found", HttpStatus.NOT_FOUND);
        }
    }
}