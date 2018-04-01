
import java.io.IOException;
import java.util.List;

public final class AllowedShoppingItemListFactory {

	public static AllowedShoppingItemList generateAllowedShoppingItemListFromFile(String filePath) throws IOException {
		List<String> linesInFile = FileUtils.getFileContentsAsListOfStrings(filePath);
		
		String [] arrayOfAllowedItemStrings = linesInFile.toArray(new String[0]);
		
		return new AllowedShoppingItemList(arrayOfAllowedItemStrings);
	}

}
