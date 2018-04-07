package DataMining;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import AppCore.UserSelectedShoppingItems;
import de.mrapp.apriori.Apriori;
import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.Output;
import de.mrapp.apriori.Transaction;

public class FrequentItemSetGenerator {
	private MessageDigest digestForHashingDataUsingSHA256 = null;

	private String hashOfCurrentData = "";

	private FrequentItemSets<Item> frequentItemSets = null;

	private void generateSHA256Digest() {
		try {
			digestForHashingDataUsingSHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Faild to locate SHA-256 hash digest.");
			e.printStackTrace();
		}
	}

	public FrequentItemSetGenerator() {
		generateSHA256Digest();
	}

	public FrequentItemSetGenerator(UserSelectedShoppingItems [] allUserSelectedShoppingItemLists) {
		generateSHA256Digest();

		updateFrequentItemSets(allUserSelectedShoppingItemLists);
	}

	public void updateFrequentItemSets(UserSelectedShoppingItems [] allUserSelectedShoppingItemLists) {
		digestForHashingDataUsingSHA256.reset();

		byte[] hashBytes = digestForHashingDataUsingSHA256.digest((allUserSelectedShoppingItemLists).toString().getBytes());

		String hashOfNewData = new String(hashBytes);

		/*
		 * if the hash is the same as the old hash -> the data has not changed ->
		 * Don't recalculate frequent item sets
		 */
		
		if (hashOfNewData.equals(hashOfCurrentData)) {
			return;
		} else {
			hashOfCurrentData = new String(hashOfNewData);
		}

		/*
		 * The next steps are essentially copy-pasted from the tutorial of the Data Mining library
		 * There is not much to explain - it's just how the library author decided
		 * things should be done
		 */
		ArrayList<Transaction<Item>> shoppingAprioriTransactionsArrayList = new ArrayList<Transaction<Item>>(
				allUserSelectedShoppingItemLists.length);
		
		for (UserSelectedShoppingItems userSelectedShoppingItems : allUserSelectedShoppingItemLists) {
			shoppingAprioriTransactionsArrayList.add(new ShoppingAprioriTransaction(userSelectedShoppingItems));
		}

		int numRulesToRetrieve = 5;
		Apriori<Item> apriori = new Apriori.Builder<Item>(numRulesToRetrieve).create();

		ShoppingAprioriDataIterable shoppingAprioriDataIterable = new ShoppingAprioriDataIterable(
				shoppingAprioriTransactionsArrayList);

		Iterable<Transaction<Item>> iterable = shoppingAprioriTransactionsArrayList;
		Output<Item> output = apriori.execute(iterable);
		frequentItemSets = output.getFrequentItemSets();

		System.out.println(String.format("frequentItemSets: %s", frequentItemSets.toString()));
	}

	public FrequentItemSets<Item> getFrequentItemSets() {
		return frequentItemSets;
	}

}
