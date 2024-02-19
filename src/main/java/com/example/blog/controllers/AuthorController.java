package com.example.blog.controllers;

import com.example.blog.exception.NoArticleFoundException;
import com.example.blog.exception.NoAuthorFoundException;
import com.example.blog.models.Author;
import com.example.blog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{authorId}")
    public Author getAuthor(@PathVariable Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new NoArticleFoundException(HttpStatus.NOT_FOUND, "No author found");
        }
    }

    @PostMapping("/authors")
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            authorRepository.deleteById(authorId);
        } else {
            throw new NoAuthorFoundException(HttpStatus.NOT_FOUND, "author already deleted");
        }
        authorRepository.deleteById(authorId);
    }

}
