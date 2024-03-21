package com.doranco.coursSpring.repository;

import com.doranco.coursSpring.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    //TODO
}
