package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.controller.api.payload.Payload;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.repository.ArticleRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ArticleApiController {

    private final ArticleRepository articleRepository;

    public ArticleApiController(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/article")
    public ResponseEntity<Payload> getAll()
    {
        var articles = articleRepository.findAll();
        Payload payload = new Payload("Get All article", articles);
        if (articles.isEmpty()) {
            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Payload> getById(@PathVariable int id)
    {
        Payload payload = new Payload();
        try {
            var article = articleRepository.findById(id).get();
            payload.setMessage("Get one Article");
            payload.setContent(article);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Article not found");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/article")
    public ResponseEntity<Payload> add(@RequestBody Article article)
    {
        Payload payload = new Payload();
        article = articleRepository.save(article);
        payload.setContent(article);
        payload.setMessage("Article created");
        return new ResponseEntity<>(payload, HttpStatus.CREATED);
    }


    @PutMapping("/article/{id}")
    public ResponseEntity<Payload> update(@RequestBody Article formArticle, @PathVariable int id)
    {
        Payload payload = new Payload();
        try {
            var article = articleRepository.findById(id).get();
            article.setTitle(formArticle.getTitle());
            article.setContent(formArticle.getContent());
            var articleUpdated = articleRepository.save(article);

            payload.setContent(articleUpdated);
            payload.setMessage("Article updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage("Article ID (" + id + ") NOT FOUND");
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Payload> deleteById(@PathVariable int id) {
        articleRepository.deleteById(id);
        return new ResponseEntity<>(new Payload("Article ID : " + id + " deleted"), HttpStatus.OK);
    }
}
