import org.bson.Document;

public interface MongoDBObjectFactory<T> {
	T generateObjectFromDocument(Document document);
}
