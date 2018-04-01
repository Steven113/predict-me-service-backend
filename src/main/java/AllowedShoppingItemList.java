import java.util.Arrays;

public class AllowedShoppingItemList {

	private final String [] allowedShoppingItems;
	
	public AllowedShoppingItemList(String [] allowedShoppingItems) {
		this.allowedShoppingItems = Arrays.copyOf(allowedShoppingItems, allowedShoppingItems.length);
	}
	
	public final String [] getAllAllowedShoppingItems() {
		String [] copyOfAllowedShoppingItems = new String[allowedShoppingItems.length];
		
		for (int index = 0; index < allowedShoppingItems.length; ++index) {
			copyOfAllowedShoppingItems[index] = new String(allowedShoppingItems[index]);
		}
		
		return copyOfAllowedShoppingItems;
	}
	
	public boolean hasItemAtIndex(int index) {
		return allowedShoppingItems.length>index && index >=0;
	}
	
	public final String getItemAtIndex(int index) {
		return new String(allowedShoppingItems[index]);
	}

}
