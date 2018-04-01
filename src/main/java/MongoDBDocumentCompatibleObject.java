import org.bson.Document;

public interface MongoDBDocumentCompatibleObject {
	Document generateMongoDBDocumentFromObject();
}
