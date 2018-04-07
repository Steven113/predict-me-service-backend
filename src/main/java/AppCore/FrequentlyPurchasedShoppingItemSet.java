package AppCore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import de.mrapp.apriori.Item;
import de.mrapp.apriori.ItemSet;

public class FrequentlyPurchasedShoppingItemSet implements Iterable<String> {
	
	private HashSet<String> chosenItems = new HashSet<>();
	
	private DayOfWeek dayOfWeek = DayOfWeek.Invalid_Day;
	
	private static final String [] validDaysOfWeek = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public FrequentlyPurchasedShoppingItemSet(ItemSet<Item> chosenItems) {
		for (Item item : chosenItems) {
			
			int indexReturnedByBinarySearch = Arrays.binarySearch(validDaysOfWeek, item.toString());
			
			if (indexReturnedByBinarySearch > 0 && indexReturnedByBinarySearch<validDaysOfWeek.length) {
				dayOfWeek = DayOfWeek.getDayOfWeekFromString(item.toString());
			} else {
				this.chosenItems.add(item.toString());
			}
		}
	}

	public HashSet<String> getChosenItems() {
		return chosenItems;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	
	public boolean hasValidDay() {
		return dayOfWeek!=dayOfWeek.Invalid_Day;
	}
	
	public boolean hasItems() {
		return chosenItems.size() > 0;
	}
	
	public boolean hasMoreThanOneItem() {
		return chosenItems.size() > 1;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return chosenItems.iterator();
	}

}
