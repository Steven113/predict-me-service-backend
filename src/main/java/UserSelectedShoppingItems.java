import java.util.HashMap;
import java.util.LinkedList;

import org.bson.Document;

public class UserSelectedShoppingItems implements MongoDBDocumentCompatibleObject {
	
	private HashMap<String, Integer> itemCounts = new HashMap<>();
	
	public UserSelectedShoppingItems(LinkedList<String> selectedItems) {
		for (String item : selectedItems) {
			if (!itemCounts.containsKey(item)) {
				itemCounts.put(item, 1);
			} else {
				itemCounts.put(item, itemCounts.get(item)+1);
			}
		}
	}

	@Override
	public Document generateMongoDBDocumentFromObject() {
		Document documentGeneratedFromObject = new Document();
		
		for (String item : itemCounts.keySet()) {
			documentGeneratedFromObject.append(item, itemCounts.get(item));
		}
		
		return documentGeneratedFromObject;
	}

}
