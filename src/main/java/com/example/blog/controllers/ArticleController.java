package com.example.blog.controllers;

import com.example.blog.exception.NoArticleFoundException;
import com.example.blog.models.Article;
import com.example.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{articleId}")
    public Article getArticle(@PathVariable Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isPresent()) {
            return article.get();
        } else {
            throw new NoArticleFoundException(HttpStatus.NOT_FOUND, "no article found");
        }
    }
}
