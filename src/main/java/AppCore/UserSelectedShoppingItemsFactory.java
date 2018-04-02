package AppCore;
import java.util.HashMap;
import java.util.HashSet;

import org.bson.Document;

import DataMining.MongoDBObjectFactory;

public class UserSelectedShoppingItemsFactory implements MongoDBObjectFactory<UserSelectedShoppingItems>{


	@Override
	public UserSelectedShoppingItems generateObjectFromDocument(Document document) {
		HashSet<String> chosenItems = new HashSet<>();
		
		int dayOfWeek = 0;
		
		for (String key : document.keySet()) {
			if (!key.equals("_id")) {
				if (key.equals("DayOfWeek")) {
					dayOfWeek = document.getInteger("DayOfWeek");
				} else {
					chosenItems.add(key);
				}
			}
		}
		
		return new UserSelectedShoppingItems(chosenItems, dayOfWeek);
	}
	
	public UserSelectedShoppingItems [] getArrayOfUserSelectedShoppingItemsFromArrayOfDocuments(Document [] documents) {
		UserSelectedShoppingItems [] result = new UserSelectedShoppingItems[documents.length];
		
		for (int n = 0; n<documents.length; ++n) {
			result[n] = generateObjectFromDocument(documents[n]);
		}
		
		return result;
	}

}
