package AppCore;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.bson.Document;

import DataMining.MongoDBDocumentCompatibleObject;

public class UserSelectedShoppingItems implements MongoDBDocumentCompatibleObject {
	
	private HashSet<String> chosenItems = new HashSet<>();
	
	private int dayOfWeek;
	
	public UserSelectedShoppingItems(LinkedList<String> selectedItems) {
		for (String item : selectedItems) {
			chosenItems.add(item);
		}
		
		dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	public UserSelectedShoppingItems(HashSet<String> itemCounts) {
		this.chosenItems = new HashSet<>(itemCounts);
	}
	
	public UserSelectedShoppingItems(HashSet<String> itemCounts, int dayOfWeek) {
		this.chosenItems = new HashSet<>(itemCounts);
		this.dayOfWeek = dayOfWeek;
	}
	
	public boolean equals(UserSelectedShoppingItems other) {
		return other.chosenItems.equals(this.chosenItems)
				&& this.chosenItems.equals(other.chosenItems)
				&& (this.dayOfWeek == other.dayOfWeek);
	}
	
	@Override
	public int hashCode() {
		return (chosenItems.toString() + dayOfWeek).hashCode();
	}
	
	@Override
	public Document generateMongoDBDocumentFromObject() {
		Document documentGeneratedFromObject = new Document();
		
		for (String item : chosenItems) {
			documentGeneratedFromObject.append(item, 1);
		}
		
		documentGeneratedFromObject.append("DayOfWeek", dayOfWeek);
		
		return documentGeneratedFromObject;
	}
	
	@Override
	public String toString() {
		return(chosenItems.toString() + dayOfWeek);
	}

	public HashSet<String> getChosenItems() {
		return new HashSet<String>(chosenItems);
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

}
