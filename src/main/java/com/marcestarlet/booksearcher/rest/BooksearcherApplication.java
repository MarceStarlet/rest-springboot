package com.marcestarlet.booksearcher.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = {"com.marcestarlet.booksearcher.rest"})
@PropertySources({ @PropertySource("classpath:application.properties") })
public class BooksearcherApplication {

    public static final String ROOT_APP = "/booksearcher/books/";

    public static void main( String[] args ){
        SpringApplication.run(BooksearcherApplication.class, args);
    }
}
