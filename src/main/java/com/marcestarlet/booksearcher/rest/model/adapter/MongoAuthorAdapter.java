/**
 *
 */
package com.marcestarlet.booksearcher.rest.model.adapter;

import org.bson.Document;

import com.marcestarlet.booksearcher.rest.model.entity.Author;
import org.springframework.stereotype.Component;

/**
 * @author MarceStarlet
 *
 * mongodb-basics
 */
@Component
public class MongoAuthorAdapter implements MongoAdapter<Author> {

    public MongoAuthorAdapter() {
    }

    @Override
    public Document toDocument( Author pojo ) {

        Document author = new Document( "name", pojo.getName() )
                .append( "biography", pojo.getBiography() );

        return author;
    }

    @Override
    public Author toPOJO( Document doc ) {

        Author author = new Author();
        author.setName( doc.getString( "name" ) );
        author.setBiography( doc.getString( "biography" ) );

        return author;
    }

}
