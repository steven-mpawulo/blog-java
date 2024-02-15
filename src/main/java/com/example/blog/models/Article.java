package com.example.blog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String content;

    protected Article() {}

    public Article(String content, String author, Long authorId) {

        this.content = content;
    }



    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "Id=" + Id +
                ", content='" + content + '\'' +
                '}';
    }
}
