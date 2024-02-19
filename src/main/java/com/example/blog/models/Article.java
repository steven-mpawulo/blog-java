package com.example.blog.models;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String content;
    @ManyToOne
    private Author author;

    protected Article() {}

    public Article(String content, Author author) {

        this.content = content;
        this.author = author;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
