package AppCore;

import java.util.LinkedList;

import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.ItemSet;

public class FrequentlyPurchasedShoppingItemSetFactory {

	public static FrequentlyPurchasedShoppingItemSet [] generateFrequentlyPurchasedShoppingItemSet(FrequentItemSets<Item> frequentItemSets) {
		LinkedList<FrequentlyPurchasedShoppingItemSet> frequentlyPurchasedItems = new LinkedList<>();
		
		for (ItemSet<Item> itemSet : frequentItemSets) {
			frequentlyPurchasedItems.add(new FrequentlyPurchasedShoppingItemSet(itemSet));
		}
		
		return frequentlyPurchasedItems.toArray(new FrequentlyPurchasedShoppingItemSet [0]);
	}

}
