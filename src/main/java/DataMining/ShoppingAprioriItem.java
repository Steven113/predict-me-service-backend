package DataMining;
import de.mrapp.apriori.Item;

public class ShoppingAprioriItem implements Item {
	
	final String itemName;
	
	public ShoppingAprioriItem(String itemName) {
		this.itemName = itemName;
	}

	
	public int hashCode() {
		return (new String(itemName)).hashCode();
	}
	
	public int compareTo(ShoppingAprioriItem arg0) {
		// TODO Auto-generated method stub
		return this.itemName.compareTo(arg0.itemName);
	}
	
	@Override
	public String toString() {
		return new String(itemName);
	}
	
	public String getItemName() {
		return new String(itemName);
	}

	@Override
	public int compareTo(Item arg0) {
		// TODO Auto-generated method stub
		return this.toString().compareTo(arg0.toString());
	}

}
