/**
 * 
 */
package com.marcestarlet.booksearcher.rest.model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * MongoDAOFactory creates DAOs for MongoDB
 * @author MarceStarlet
 *
 * mongodb-basics
 */
@Component
public class MongoDAOFactory {

	private static final String MONGO_URI = "mongodb://localhost";
	private static final String MONGO_DATABASE = "booksearcher";
	
	private static MongoDAOFactory factory = new MongoDAOFactory();;

	@SuppressWarnings("rawtypes")
	private static Map<String, DAO> map = new HashMap<>();
	
	private MongoClient asyncMongoClient;
    private MongoDatabase asyncDB;
    
    private com.mongodb.MongoClient syncMongoClient;
    private com.mongodb.client.MongoDatabase syncDB;

    @Autowired
    @Qualifier("mongo-book")
    DAO bookDAO; // use spring auto-wired to get all DAOs
	
	private MongoDAOFactory() {
        asyncMongoClient = MongoClients.create( new ConnectionString( MONGO_URI ) );
        asyncDB = asyncMongoClient.getDatabase( MONGO_DATABASE );

        syncMongoClient = new com.mongodb.MongoClient ( new MongoClientURI( MONGO_URI ) );
        syncDB = syncMongoClient.getDatabase( MONGO_DATABASE );
	}

    /**
     * initializes the DAO map after the MongoDB objects are set
     * in the constructor
     */
	@PostConstruct
	public void init(){
        map.put( "book", bookDAO );
    }
	
	/**
	 * get a MongoDAOFactory instance
	 * @return MongoDAOFactory
	 */
	public static MongoDAOFactory getInstance(){
		if(null != factory ){
			return factory;
		}else{
			return new MongoDAOFactory();
		}
	}
	
	/**
	 * get Mongo DAO
	 * @param daoType
	 * @return DAO<"Type">
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("rawtypes")
	public DAO getDAO( String daoType ) throws IllegalArgumentException{
		
		DAO dao = map.get( daoType );
		
		if( null != dao ){
			return dao;
		}else{
			throw new IllegalArgumentException("No such dao type \"" + daoType + "\" found");
		}
	}

    /**
     * get the AsyncDB object of MongoDB
     * @return MongoDatabase async
     */
    public MongoDatabase getAsyncDB() {
        return asyncDB;
    }

    /**
     * get the SyncDB object of MongoDB
     * @return MongoDatabase sync
     */
    public com.mongodb.client.MongoDatabase getSyncDB() {
        return syncDB;
    }
}
