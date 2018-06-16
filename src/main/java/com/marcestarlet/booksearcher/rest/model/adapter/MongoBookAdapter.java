/**
 *
 */
package com.marcestarlet.booksearcher.rest.model.adapter;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.marcestarlet.booksearcher.rest.model.entity.Author;
import com.marcestarlet.booksearcher.rest.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MongoDB Adapter for a Book
 * @author MarceStarlet
 *
 * mongodb-basics
 */
@Component
public class MongoBookAdapter implements MongoAdapter<Book>{

    @Autowired
    private MongoAuthorAdapter authorAdapter;

    public MongoBookAdapter() {
    }

    @Override
    public Document toDocument( Book pojo ) {

        List<Document> authors = new ArrayList<>( pojo.getAuthors().size() );

        for( Author author : pojo.getAuthors() ){
            authors.add( authorAdapter.toDocument( author ) );
        }

        Document book = new Document( "isbn", pojo.getIsbn() )
                .append( "title", pojo.getTitle() )
                .append( "description", pojo.getDescription() )
                .append( "edition", pojo.getEdition() )
                .append( "editorials", pojo.getEditorial() )
                .append( "classifications", pojo.getClassifications() )
                .append( "authors", authors );

        return book;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Book toPOJO( Document doc ) {

        List<Document> docAuthors = (List<Document>) doc.get( "authors" );
        List<Author> authors = new ArrayList<>( docAuthors.size() );
        for( Document d : docAuthors ) {
            authors.add( authorAdapter.toPOJO( d ) );
        }

        Book book = new Book();
        book.setIsbn( doc.getString( "isbn" ) );
        book.setTitle(  doc.getString( "title" ) );
        book.setDescription( doc.getString( "description" ) );
        book.setEdition( doc.getInteger( "edition" ) );
        book.setEditorial( doc.getString( "editorial" ) );
        book.setClassifications( (List<String>) doc.get( "classifications" ) );
        book.setAuthors( authors );

        return book;
    }

}
