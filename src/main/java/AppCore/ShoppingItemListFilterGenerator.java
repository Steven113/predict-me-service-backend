package AppCore;

import java.util.HashSet;

import org.bson.Document;
import org.bson.conversions.Bson;

import DataMining.MongoDBCollectionFilter;

public class ShoppingItemListFilterGenerator {

	public static MongoDBCollectionFilter generateBsonFilterDocumentForShoppingList(UserSelectedShoppingItems userSelectedShoppingItems) {
		MongoDBCollectionFilter filterDocument = new MongoDBCollectionFilter();
		
		HashSet<String> itemsUserSelected = userSelectedShoppingItems.getChosenItems();
		
		for (String item : itemsUserSelected) {
			filterDocument.append(item, 1);
		}
		
		filterDocument.append("DayOfWeek", userSelectedShoppingItems.getDayOfWeek().toString());
		
		return filterDocument;
	}

}
