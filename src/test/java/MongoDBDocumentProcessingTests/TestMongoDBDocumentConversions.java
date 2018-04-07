package MongoDBDocumentProcessingTests;
import java.util.HashSet;

import static org.junit.Assert.*;

import org.junit.Test;

import AppCore.UserSelectedShoppingItems;
import AppCore.UserSelectedShoppingItemsFactory;

public class TestMongoDBDocumentConversions {
	
	/**
	 * Generates random test documents and converts them to and from the Document format 
	 * to ensure the conversion is correct.
	 */
	@Test
	public void testConversionBetweenObjectsAndMongoDBDocuments() {
		RandomStringGenerator randomStringGenerator = new RandomStringGenerator("0");
		
		UserSelectedShoppingItemsFactory userSelectedShoppingItemsFactory = new UserSelectedShoppingItemsFactory();
		
		for (int testCase = 0; testCase < 100; ++testCase) {
			for (int numKeys = 1; numKeys<500; ++numKeys) {
				HashSet<String> shoppingListTestMapping = new HashSet<>();
				
				for (int keyNo = 0; keyNo<numKeys; ++keyNo) {
					String key = randomStringGenerator.getRandomString();
					shoppingListTestMapping.add(key);
				}
				
				UserSelectedShoppingItems userSelectedShoppingItems = new UserSelectedShoppingItems(shoppingListTestMapping);
				
				UserSelectedShoppingItems userSelectedShoppingItemsRetrievedFromDocument = userSelectedShoppingItemsFactory.generateObjectFromDocument(userSelectedShoppingItems.generateMongoDBDocumentFromObject());
				
				assertTrue("Purchased item lists are not equal",userSelectedShoppingItems.equals(userSelectedShoppingItemsRetrievedFromDocument));
			}
		}
		
		
	}

}
