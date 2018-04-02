package AppCore;

import de.mrapp.apriori.AssociationRule;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.RuleSet;

public class AssociationRuleForShoppingItems {
	
	private FrequentlyPurchasedShoppingItemSet itemsUserBrought = null;
	 
	private FrequentlyPurchasedShoppingItemSet itemsThatUserWillBuy = null;
	
	public AssociationRuleForShoppingItems(AssociationRule<Item> associationRules) {
		itemsUserBrought = new FrequentlyPurchasedShoppingItemSet(associationRules.getHead());
		
		itemsThatUserWillBuy = new FrequentlyPurchasedShoppingItemSet(associationRules.getBody());
	}

	public FrequentlyPurchasedShoppingItemSet getItemsUserBrought() {
		return itemsUserBrought;
	}

	public FrequentlyPurchasedShoppingItemSet getItemsThatUserWillBuy() {
		return itemsThatUserWillBuy;
	}

}
