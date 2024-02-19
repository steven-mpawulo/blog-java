package com.example.blog.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NoAuthorFoundException extends ResponseStatusException {
    public NoAuthorFoundException(HttpStatusCode code, String message) {
        super(code, message);
    }
}
