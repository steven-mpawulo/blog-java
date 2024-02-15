package com.example.blog.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NoArticleFoundException extends ResponseStatusException {

    public NoArticleFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
