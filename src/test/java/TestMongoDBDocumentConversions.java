import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import AppCore.UserSelectedShoppingItems;
import AppCore.UserSelectedShoppingItemsFactory;

class TestMongoDBDocumentConversions {
	
	@Test
	void testConversionBetweenObjectsAndMongoDBDocuments() {
		RandomStringGenerator randomStringGenerator = new RandomStringGenerator("0");
		Random random = new Random(0);
		
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
				
				Assert.assertTrue("Purchased item lists are not equal", userSelectedShoppingItems.equals(userSelectedShoppingItemsRetrievedFromDocument));
			}
		}
		
		
	}

}
