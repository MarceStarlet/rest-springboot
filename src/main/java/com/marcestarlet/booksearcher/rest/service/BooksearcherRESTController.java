package com.marcestarlet.booksearcher.rest.service;

import com.marcestarlet.booksearcher.rest.BooksearcherApplication;
import com.marcestarlet.booksearcher.rest.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BooksearcherApplication.ROOT_APP)
public class BooksearcherRESTController {

    public static final String CREATE_BOOK_ENDPOINT = "create";
    public static final String UPDATE_BOOK_ENDPOINT = "update";
    public static final String REMOVE_BOOK_ENDPOINT = "remove/{isbn}";
    public static final String FIND_ALL_BOOK_ENDPOINT = "find/all";
    public static final String FIND_ISBN_BOOK_ENDPOINT = "find/isbn/{isbn}";
    public static final String FIND_FILTER_BOOK_ENDPOINT = "find/filter/{filter}";

    @Autowired
    public BookServiceImp bookService;

    @PostMapping(CREATE_BOOK_ENDPOINT)
    public ResponseEntity<Void> createBook(@PathVariable Book book){

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        if( null != book ){
            bookService.createBook(book);
        }else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PutMapping(UPDATE_BOOK_ENDPOINT)
    public ResponseEntity<Void> updateBook(@PathVariable Book book){

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        if( null != book ){
            bookService.updateBook(book);
        }else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @DeleteMapping(REMOVE_BOOK_ENDPOINT)
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn){

        ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.OK);
        if( null != isbn && !isbn.isEmpty() ){
            bookService.deleteBook(isbn);
        }else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @GetMapping(FIND_ALL_BOOK_ENDPOINT)
    public List<Book> findAllBooks(){

        return bookService.findAllBooks();
    }

    @GetMapping(FIND_ISBN_BOOK_ENDPOINT)
    public Book findBookByISBN(@PathVariable String isbn){

        return bookService.findBookByISBN(isbn);
    }

    @GetMapping(FIND_FILTER_BOOK_ENDPOINT)
    public List<Book> findBookByFilter(@PathVariable String filter){

        return bookService.findBookByFilter(filter);
    }

}
