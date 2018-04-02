package DataMining;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import AppCore.UserSelectedShoppingItems;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.Transaction;

public class ShoppingAprioriTransaction implements Transaction<Item> {
	
	ArrayList<Item> shoppingItems = null;
	
	public ShoppingAprioriTransaction(UserSelectedShoppingItems userSelectedShoppingItems) {
		
		HashSet<String> itemsThatUserSelected = (userSelectedShoppingItems.getChosenItems());
		
		shoppingItems = new ArrayList<>(itemsThatUserSelected.size());
		
		for (String key : itemsThatUserSelected) {
			shoppingItems.add(new ShoppingAprioriItem(key));
		}
		
		shoppingItems.add(new ShoppingAprioriItem(userSelectedShoppingItems.getDayOfWeek()+""));
	}
	
	@Override
	public Iterator<Item> iterator() {
		return shoppingItems.iterator();
	}
	
	@Override
	public int hashCode() {
		return shoppingItems.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return this.hashCode() == other.hashCode();
	}
	
	@Override
	public String toString(){
		return shoppingItems.toString();
	}
	
}