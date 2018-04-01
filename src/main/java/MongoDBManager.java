import java.util.HashMap;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBManager {
	private MongoClient mongoClient = null;
	private MongoDatabase mongoDatabase = null;
	
	MongoDBManager(String mongoDBServerIP, int mongoDBServerPort, String mongoDatabaseName) {
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

}
