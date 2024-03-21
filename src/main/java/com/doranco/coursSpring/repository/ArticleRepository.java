package com.doranco.coursSpring.repository;

import com.doranco.coursSpring.model.entity.Article;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    public Optional<Article> findById(Long id);

    public void deleteById(Long id);

}
