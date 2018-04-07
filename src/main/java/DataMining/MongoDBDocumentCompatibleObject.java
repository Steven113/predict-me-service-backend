package DataMining;
import org.bson.Document;

/**
 * Interface for objects that can be converted to MongoDB Document objects
 * for the API
 * @author Steven Tupper
 *
 */
public interface MongoDBDocumentCompatibleObject {
	Document generateMongoDBDocumentFromObject();
}
