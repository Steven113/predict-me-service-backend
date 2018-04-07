package DataMining;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

import AppCore.UserSelectedShoppingItems;
import de.mrapp.apriori.Apriori;
import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.Output;
import de.mrapp.apriori.RuleSet;
import de.mrapp.apriori.Transaction;

public class AssociationRuleGenerator {
	
	private MessageDigest digestForHashingDataUsingSHA256 = null;
	
	private String hashOfCurrentData = "";
	
	private RuleSet<Item> associationRules = null;
	
	private void generateSHA256Digest() {
		try {
			digestForHashingDataUsingSHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Faild to locate SHA-256 hash digest.");
			e.printStackTrace();
		}
	}
	
	public AssociationRuleGenerator() {
		generateSHA256Digest();
	}
	
	public AssociationRuleGenerator(UserSelectedShoppingItems [] allUserSelectedShoppingItemLists) {
		generateSHA256Digest();
		updateAssociationRules(allUserSelectedShoppingItemLists);
	}
	
	public void updateAssociationRules(UserSelectedShoppingItems [] allUserSelectedShoppingItemLists) {
		digestForHashingDataUsingSHA256.reset();
		
		byte [] hashBytes = digestForHashingDataUsingSHA256.digest((allUserSelectedShoppingItemLists).toString().getBytes());
		
		String hashOfNewData = new String(hashBytes);
		
		/*
		 * if the hash is the same as the old hash -> the data has not changed ->
		 * Don't recalculate association rules
		 */
		
		if (hashOfNewData.equals(hashOfCurrentData)) {
			return;
		} else {
			hashOfCurrentData = new String(hashOfNewData);
		}
		
		ArrayList<Transaction<Item>> shoppingAprioriTransactionsArrayList = new ArrayList<Transaction<Item>>(
				allUserSelectedShoppingItemLists.length);
		
		for (UserSelectedShoppingItems userSelectedShoppingItems : allUserSelectedShoppingItemLists) {
			shoppingAprioriTransactionsArrayList.add(new ShoppingAprioriTransaction(userSelectedShoppingItems));
		}
		
		/*
		 * The next steps are essentially copy-pasted from the tutorial of the Data Mining library
		 * There is not much to explain - it's just how the library author decided
		 * things should be done
		 */
		
		double minSupport = 0.5;
		double minConfidence = 0.95;
		int ruleCount = 15;
		Apriori<Item> apriori = new Apriori.Builder<Item>(minSupport).generateRules(ruleCount).confidenceDelta(0.1).create();
		
		ShoppingAprioriDataIterable shoppingAprioriDataIterable = new ShoppingAprioriDataIterable(
				shoppingAprioriTransactionsArrayList);
		
		Iterable<Transaction<Item>> iterable = shoppingAprioriDataIterable;
		Output<Item> output = apriori.execute(iterable);
		associationRules = output.getRuleSet();
		
		System.out.println(String.format("associationRules: %s", associationRules.toString()));
	}

	public RuleSet<Item> getAssociationRules() {
		return associationRules;
	}

}
