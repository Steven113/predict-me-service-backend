package AppCore;

import java.util.LinkedList;

import de.mrapp.apriori.AssociationRule;
import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.ItemSet;
import de.mrapp.apriori.RuleSet;

public class AssociationRuleForShoppingItemsFactory {

	public static AssociationRuleForShoppingItems [] generateAssociationRulesForShoppingItemSets(RuleSet<Item> associationRules) {
		LinkedList<AssociationRuleForShoppingItems> frequentlyPurchasedItems = new LinkedList<>();
		
		for (AssociationRule<Item> itemSet : associationRules) {
			frequentlyPurchasedItems.add(new AssociationRuleForShoppingItems(itemSet));
		}
		
		return frequentlyPurchasedItems.toArray(new AssociationRuleForShoppingItems [0]);
	}

}
