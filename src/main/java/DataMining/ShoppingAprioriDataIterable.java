package DataMining;
import java.util.Iterator;
import java.util.List;

import de.mrapp.apriori.Item;
import de.mrapp.apriori.Transaction;

/**
 * A object matching the Iterable<Transaction<Item>> interface, for bridging
 * between the Data Mining API and this App
 * @author Steven Tupper
 *
 */
public class ShoppingAprioriDataIterable implements Iterable<Transaction<Item>> {
	
	List<Transaction<Item>> shoppingDataIterable = null;
	
	public ShoppingAprioriDataIterable(List<Transaction<Item>> shoppingDataIterable) {
		this.shoppingDataIterable = shoppingDataIterable;
	}

	@Override
	public Iterator<Transaction<Item>> iterator() {
		return shoppingDataIterable.iterator();
	}

}
