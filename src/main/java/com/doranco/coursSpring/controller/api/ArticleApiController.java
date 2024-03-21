package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.controller.exception.NotFoundException;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.ArticleRepository;
import com.doranco.coursSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController()
public class ArticleApiController {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleApiController(ArticleRepository articleRepository, UserRepository userRepository)
    {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/article")
    public ResponseEntity<Payload> getAll() {
        Payload payload = new Payload();

        List<Article> articles = articleRepository.findAll();

        if (articles.isEmpty()) {
            payload.setMessage("There are no articles to retrieve.");
            payload.setContent(null);

            return new ResponseEntity<>(payload, HttpStatus.NO_CONTENT);
        }

        payload.setMessage(String.format(
                "%d %s found.",
                articles.size(),
                articles.size() > 1 ? "articles" : "article"
        ));
        payload.setContent(articles);

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<Payload> getById(@PathVariable int id) {
        Payload payload = new Payload();
        try {
            Article article = articleRepository.findById(id).get();
            payload.setMessage("Successfully get article.");
            payload.setContent(article);
        } catch (NoSuchElementException e) {
            payload.setMessage(String.format("Article %d doesn't exists.", id));
            payload.setContent(null);
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @PostMapping("/api/article")
    public ResponseEntity<Payload> add(@RequestBody Article bodyArticle) {
        Payload payload = new Payload();

        // Check un conflit avec article.id n'est pas nécessaire, car il n'est visiblement pas pris en compte.

        if (bodyArticle.getTitle() == null
                || bodyArticle.getUser() == null
                || bodyArticle.getUser().getId() == null) {
            payload.setMessage("Incomplete body request.");
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findById(bodyArticle.getUser().getId());
        if (user.isEmpty()) {
            payload.setMessage(String.format(
                    "Article user id %d doesn't exists.",
                    bodyArticle.getUser().getId()
            ));
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }


        Article savedArticle = articleRepository.save(bodyArticle);
        savedArticle.setUser(user.get());
        payload.setMessage(String.format(
                "Article id %d created.",
                savedArticle.getId()
        ));
        payload.setContent(savedArticle);

        return new ResponseEntity<>(payload, HttpStatus.CREATED);

    }


    @PutMapping("/api/article/{id}")
    public ResponseEntity<Payload> update(@PathVariable int id, Article formArticle) {
        Payload payload = new Payload();

        try {
            Article article = articleRepository.findById(id).get();
            article.setTitle(formArticle.getTitle());
            article.setContent(formArticle.getContent());
            payload.setMessage(String.format(
                    "Article id %d updated.",
                    article.getId()
            ));
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            payload.setMessage(String.format(
                    "Article id %d doesn't exists.",
                    id
            ));
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Payload> deleteById(int id) {
        Payload payload = new Payload();

        try {
            articleRepository.deleteById(id);
            payload.setMessage(String.format(
                    "Article id %d deleted.",
                    id
            ));
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            payload.setMessage(String.format(
                    "Article id %d doesn't exists.",
                    id
            ));
            return new ResponseEntity<>(payload, HttpStatus.NOT_FOUND);
        }
    }

}
