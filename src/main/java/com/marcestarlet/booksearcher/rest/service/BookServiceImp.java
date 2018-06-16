/**
 *
 */
package com.marcestarlet.booksearcher.rest.service;

import java.util.List;

import com.marcestarlet.booksearcher.rest.exceptions.BookNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marcestarlet.booksearcher.rest.model.dao.BookDAO;
import com.marcestarlet.booksearcher.rest.model.dao.MongoDAOFactory;
import com.marcestarlet.booksearcher.rest.model.entity.Book;
import org.springframework.stereotype.Component;

/**
 * BookServiceImp implements the operations exposed as
 * services.
 * @author MarceStarlet
 *
 * mongodb-basics
 */
@Component
public class BookServiceImp implements BookService {

    private Logger log = LoggerFactory.getLogger( BookServiceImp.class );

    private static final String BOOK_DAO = "book";
    private BookDAO bookDAO = null;

    public BookServiceImp() {
        bookDAO = (BookDAO) MongoDAOFactory.getInstance().getDAO( BOOK_DAO );
    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#createBook(com.marcestarlet.booksearcher.rest.model.entity.Book)
     */
    @Override
    public void createBook(Book book) {
        log.info("Creating a new Book...");

        bookDAO.create( book );

    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#updateBook(com.marcestarlet.booksearcher.rest.model.entity.Book)
     */
    @Override
    public void updateBook(Book book) {
        log.info( "Updating book {} ...", book.getIsbn() );

        bookDAO.update( book );

    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#deleteBook(java.lang.String)
     */
    @Override
    public void deleteBook(String isbn) {
        log.info( "Deleting book {} ...", isbn );

        bookDAO.delete( isbn );

    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#findAllBooks()
     */
    @Override
    public List<Book> findAllBooks() throws BookNotFoundException {
        log.info( "Finding all books ..." );

        List<Book> books = bookDAO.findAll();

        if( null == books || books.isEmpty()){
            throw new BookNotFoundException();
        }

        return books;
    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#findBookByISBN(java.lang.String)
     */
    @Override
    public Book findBookByISBN(String isbn) throws BookNotFoundException {
        log.info( "Finding book {} ...", isbn );

        Book book = bookDAO.findById( isbn );

        if( null == book ){
            throw new BookNotFoundException( isbn );
        }

        return book;

    }

    /* (non-Javadoc)
     * @see com.marcestarlet.booksearcher.rest.service.BookService#findBookByFilter(java.lang.String)
     */
    @Override
    public List<Book> findBookByFilter(String filter) throws BookNotFoundException {
        log.info( "Finding books that match {} ...", filter );

        List<Book> books = bookDAO.findByFilters( filter );

        if( null == books || books.isEmpty()){
            throw new BookNotFoundException();
        }

        return books;

    }

}
