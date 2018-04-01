import java.util.HashMap;
import java.util.HashSet;

import org.bson.Document;

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

}
