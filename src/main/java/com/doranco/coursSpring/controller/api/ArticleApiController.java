package com.doranco.coursSpring.controller.api;

import com.doranco.coursSpring.controller.exception.NotFoundException;
import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import com.doranco.coursSpring.repository.ArticleRepository;
import com.doranco.coursSpring.repository.UserRepository;
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
    public List<Article> getAll()
    {
        return articleRepository.findAll();
    }

    @GetMapping("/api/article/{id}")
    public Optional<Article> getById(@PathVariable int id)
    {
        return articleRepository.findById(id);
    }

    @PostMapping("/api/article")
    public Article add(@RequestBody Article article) throws NotFoundException {
        try {
            User user = userRepository.findById(article.getUser().getId()).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException();
        }
        return articleRepository.save(article);
    }


    @PutMapping("/api/article/{id}")
    public Article update(@PathVariable int id, Article formArticle) throws NotFoundException {
        Optional<Article> byId = articleRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException();
        }

        byId.get().setTitle(formArticle.getTitle());
        byId.get().setContent(formArticle.getContent());
        return articleRepository.save(byId.get());
    }

    @DeleteMapping("/api/article/{id}")
    public String deleteById(int id) throws NotFoundException {
        Optional<Article> byId = articleRepository.findById(id);
        if (byId.isEmpty()) {
            throw new NotFoundException();
        }
        articleRepository.deleteById(id);
        return "Article ID : " + id + " deleted.";
    }

}
