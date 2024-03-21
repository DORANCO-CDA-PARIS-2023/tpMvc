package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.controller.api.payload.Payload;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.ArticleRepository;
import com.doranco.coursSpring.repository.UserRepository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.PatternSyntaxException;

@RestController
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleApiController(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/article")
    public ResponseEntity<Payload> getAll() {
        var articles = articleRepository.findAll();
        Payload payload = new Payload("Get All articles", articles);
        if(articles.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Payload> getById(@PathVariable int id) 
    {
        Payload payload = new Payload();
        try {
            var article = articleRepository.findById(id).get();
            payload.setMessage("Get one article");
            payload.setContent(article);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Article not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/article")
    public ResponseEntity<Payload> add(@RequestBody Article article) {
        Payload payload = new Payload();
        article = articleRepository.save(article);
        payload.setContent(article);
        payload.setMessage("Article Created");
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }
    
    @PutMapping("/api/article/{id}")
    public ResponseEntity<Payload> update(@PathVariable int id, @RequestBody Article formArticle) {
        Payload payload = new Payload();

        try {
            var article = articleRepository.findById(id).get();

            article.setTitle(formArticle.getTitle());
            article.setContent(formArticle.getContent());

            payload.setContent(article);
            payload.setMessage("Article Updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            payload.setMessage("Article ID (" + id + ") not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Payload> deleteById(@PathVariable int id) {
        Payload payload = new Payload();

        try {
            articleRepository.deleteById(id);

            payload.setMessage("Article Deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Article ID (" + id + ") not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }
}