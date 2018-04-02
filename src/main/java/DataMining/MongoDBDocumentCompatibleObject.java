package DataMining;
import org.bson.Document;

public interface MongoDBDocumentCompatibleObject {
	Document generateMongoDBDocumentFromObject();
}
