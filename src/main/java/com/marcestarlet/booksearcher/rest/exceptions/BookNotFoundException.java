package com.marcestarlet.booksearcher.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(){
        super("Could not find any books");
    }

    public BookNotFoundException(String isbn){
        super("Could not find book: " + isbn);
    }
}

