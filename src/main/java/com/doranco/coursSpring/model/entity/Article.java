package com.doranco.coursSpring.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, name = "title")
    private String title;
    @Column(columnDefinition = "TEXT", name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(nullable = false, name = "creation_date")
    private LocalDateTime creationDate;

    public Article() {
        this.creationDate = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime dateTime) {
        this.creationDate = dateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + user +
                ", dateTime=" + creationDate +
                '}';
    }
}
