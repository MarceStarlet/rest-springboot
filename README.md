# rest-springboot
A REST API for a book searcher using Spring Boot & MongoDB.

## Technologies
* Spring boot 2.0.2
* MongoDB API 3.6.3
* Bootstrap 4.1.1
* Bootswatch "Litera" theme
* jQuery

### How to start ...
Install MongoDB and use the "*booksearcher*" database, create a "*books*" collection. Use the mongoimport function to import the [book.json](https://github.com/MarceStarlet/mongodb-basics/blob/master/scripts/books.json) into the **books** collection:

 ```mongoimport --db booksearcher --collection books --file /books.json```

Run the ```BooksearcherApplication.java``` class, this boot will load a the REST services on ```localhost:8090```. To test the REST API by using a client tool like cURL or postman, use the following paths:
```
/booksearcher
/books
	/create - POST
	/update - PUT
	/remove/{isbn} - DELETE
	/find
	    /all - GET
	    /isbn/{isbn} - GET
	    /filter/{filter} - GET
```
To test the "find" operations of the REST API use the ```localhost:8090/index.html``` source that is a web page that let you enter different filters to do a search of a book.

### TO-DO
* The rest of the operations on the UI using Bootstrap & jQuery to consume the Web Services coded.
    * Create
    * Update
    * Delete
* Get the MongoDB host:port from the application.properties.
* Get the URL to connect to the Web Services from a configuration file.

