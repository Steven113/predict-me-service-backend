package DataMining;
import org.bson.Document;

/**
 * Interface for objects which can create a object of type T from a Document
 * object retrieved via the MongoDB API
 * @author Steven Tupper
 *
 * @param <T>
 */
public interface MongoDBObjectFactory<T> {
	T generateObjectFromDocument(Document document);
}
