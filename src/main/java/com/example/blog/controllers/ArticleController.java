package com.example.blog.controllers;

import com.example.blog.exception.NoArticleFoundException;
import com.example.blog.exception.NoAuthorFoundException;
import com.example.blog.models.Article;
import com.example.blog.models.Author;
import com.example.blog.repositories.ArticleRepository;
import com.example.blog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;

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

    @PostMapping("/articles")
    public Article addArticle(@RequestBody Map<String, String> articleData) {
        String authorId = articleData.get("authorId");
        String content = articleData.get("content");
        Optional<Author> author = authorRepository.findById(Long.parseLong(authorId));

        if (author.isPresent()) {
            Author actualAuthor = author.get();
            Article article = new Article(content, actualAuthor);
            List<Article> listOfArticles = actualAuthor.getArticles();
            listOfArticles.add(article);
            actualAuthor.setArticles(listOfArticles);
            authorRepository.save(actualAuthor);
            return articleRepository.save(article);
        } else {
            throw new NoAuthorFoundException(HttpStatus.NOT_FOUND, "Failed to save article since no author found");
        }


    }
}
