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
	private MessageDigest digest = null;

	private String hashOfCurrentData = "";

	private FrequentItemSets<Item> frequentItemSets = null;

	private void generateSHA256Digest() {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Faild to locate SHA-256 hash digest.");
			e.printStackTrace();
		}
	}

	public FrequentItemSetGenerator() {
		generateSHA256Digest();
	}

	public FrequentItemSetGenerator(LinkedList<UserSelectedShoppingItems> allUserSelectedShoppingItemLists) {
		generateSHA256Digest();

		updateFrequentItemSets(allUserSelectedShoppingItemLists);
	}

	public void updateFrequentItemSets(LinkedList<UserSelectedShoppingItems> allUserSelectedShoppingItemLists) {
		digest.reset();

		byte[] hashBytes = digest.digest((allUserSelectedShoppingItemLists).toString().getBytes());

		String hashOfNewData = new String(hashBytes);

		if (hashOfNewData.equals(hashOfCurrentData)) {
			return;
		} else {
			hashOfCurrentData = new String(hashOfNewData);
		}

		ArrayList<Transaction<Item>> shoppingAprioriTransactionsArrayList = new ArrayList<Transaction<Item>>(
				allUserSelectedShoppingItemLists.size());

		Object[] arrayOfShoppingAprioriTransacction = allUserSelectedShoppingItemLists.stream()
				.map(ShoppingAprioriTransaction::new).toArray();

		for (Object shoppingAprioriTransactionItem : arrayOfShoppingAprioriTransacction) {
			shoppingAprioriTransactionsArrayList.add((ShoppingAprioriTransaction) shoppingAprioriTransactionItem);
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
