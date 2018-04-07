package DataMining;
import java.util.HashMap;
import java.util.LinkedList;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Manages access to a MongoDB Database through the API
 * @author Steven Tupper
 *
 */
public class MongoDBManager {
	private MongoClient mongoClient = null;
	private MongoDatabase mongoDatabase = null;
	
	public MongoDBManager(String mongoDBServerIP, int mongoDBServerPort, String mongoDatabaseName) {
		mongoClient = new MongoClient(mongoDBServerIP, mongoDBServerPort);
		mongoDatabase = mongoClient.getDatabase(mongoDatabaseName);
	}
	
	public MongoCollection<Document> getCollectionFromDatabase(String collectionName){
		return mongoDatabase.getCollection(collectionName);
	}
	
	public void insertObjectIntoCollectionAsObject(String collectionName, MongoDBDocumentCompatibleObject object) {
		MongoCollection<Document> collectionDocuments = getCollectionFromDatabase(collectionName);
		try {
			collectionDocuments.insertOne(object.generateMongoDBDocumentFromObject());
		} catch (Exception e) {
			System.err.println("MongoDB insertion error");
			System.out.println(e);
		}
		
		
	}
	
	public Document [] getAllDocumentsFromCollection(String collectionName) {
		LinkedList<Document> documentsInCollection = new LinkedList<>();
		
		MongoCollection<Document> collectionDocuments = getCollectionFromDatabase(collectionName);
		
		//frustratingly convoluted way to put documents into LinkedList
		collectionDocuments.find().forEach(new Block<Document>() {
		       @Override
		       public void apply(final Document document) {
		    	   documentsInCollection.add(document);
		       }
		});
		
		return documentsInCollection.toArray(new Document[0]);
		
	}
	

}
