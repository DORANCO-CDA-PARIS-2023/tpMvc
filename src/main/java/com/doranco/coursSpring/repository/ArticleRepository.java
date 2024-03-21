package com.doranco.coursSpring.repository;

import com.doranco.coursSpring.model.entity.Article;
import com.doranco.coursSpring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByUser(User user);

}
