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
	
private MessageDigest digest = null;
	
	private String hashOfCurrentData = "";
	
	private RuleSet<Item> associationRules = null;
	
	private void generateSHA256Digest() {
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Faild to locate SHA-256 hash digest.");
			e.printStackTrace();
		}
	}
	
	public AssociationRuleGenerator() {
		generateSHA256Digest();
	}
	
	public AssociationRuleGenerator(LinkedList<UserSelectedShoppingItems> allUserSelectedShoppingItemLists) {
		generateSHA256Digest();
		updateAssociationRules(allUserSelectedShoppingItemLists);
	}
	
	public void updateAssociationRules(LinkedList<UserSelectedShoppingItems> allUserSelectedShoppingItemLists) {
		digest.reset();
		
		byte [] hashBytes = digest.digest((allUserSelectedShoppingItemLists).toString().getBytes());
		
		String hashOfNewData = new String(hashBytes);
		
		if (hashOfNewData.equals(hashOfCurrentData)) {
			return;
		} else {
			hashOfCurrentData = new String(hashOfNewData);
		}
		
		ArrayList<Transaction<Item>> shoppingAprioriTransactionsArrayList = new ArrayList<Transaction<Item>>(allUserSelectedShoppingItemLists.size());
		
		Object [] arrayOfShoppingAprioriTransacction = allUserSelectedShoppingItemLists.stream().map(ShoppingAprioriTransaction::new).toArray();
		
		for (Object shoppingAprioriTransactionItem : arrayOfShoppingAprioriTransacction) {
			shoppingAprioriTransactionsArrayList.add((ShoppingAprioriTransaction)shoppingAprioriTransactionItem);
		}
		
		double minSupport = 0.5;
		double minConfidence = 0.95;
		int ruleCount = 15;
		Apriori<Item> apriori = new Apriori.Builder<Item>(minSupport).generateRules(minConfidence).confidenceDelta(0.1).create();
		
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
