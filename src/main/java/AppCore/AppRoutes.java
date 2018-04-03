package AppCore;
import static spark.Spark.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import DataMining.AssociationRuleGenerator;
import DataMining.FrequentItemSetGenerator;
import DataMining.MongoDBCollectionFilter;
import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import de.mrapp.apriori.RuleSet;
import freemarker.template.TemplateException;
import spark.Route;

public class AppRoutes {
	
	private static UserSelectedShoppingItemsFactory userSelectedShoppingItemsFactory = new UserSelectedShoppingItemsFactory();
	
	private static FrequentItemSetGenerator frequentItemSetGenerator = new FrequentItemSetGenerator();
	
	private static AssociationRuleGenerator associationRuleGenerator = new AssociationRuleGenerator();
	
	public static Route userShoppingListFormPage = (req, res) -> {
		PredictionAppConfig predictionAppConfig = App.getPredictionAppConfig();
		
		Map values = new HashMap();
    	values.put("ip", predictionAppConfig.getServerIP());
    	values.put("port", Integer.toString(predictionAppConfig.getPortToRunServerOn()));
    	values.put("allowedShoppingItemList", predictionAppConfig.getAllowedShoppingItemList().getAllAllowedShoppingItems());
    	values.put("maxNumItemsForShoppingList",5);
    	
    	try {
			String htmlFilledIn = predictionAppConfig.getHtmlTemplatingEngineInstance().generateTemplatedHTML("index.html", values);
			return htmlFilledIn;
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Internal server error";
		}
	};
	
	public static Route dataSubmissionPage = (req,res) -> {
		LinkedList<String> userProvidedItems = new LinkedList<>();
		
		for (String queryParamName : req.queryParams()) {
			userProvidedItems.add(queryParamName);
		}
		
		UserSelectedShoppingItems userSelectedShoppingItems = new UserSelectedShoppingItems(userProvidedItems);
		
		App.putObjectInMongoDBCollection(userSelectedShoppingItems);
		
		MongoDBCollectionFilter mongoDBCollectionFilter = ShoppingItemListFilterGenerator.generateBsonFilterDocumentForShoppingList(userSelectedShoppingItems);
		
		UserSelectedShoppingItems [] userSelectedShoppingItemsFromDB = userSelectedShoppingItemsFactory.getArrayOfUserSelectedShoppingItemsFromArrayOfDocuments(App.getAllDocumentsFromCollection(mongoDBCollectionFilter));
		
		LinkedList<UserSelectedShoppingItems> userSelectedShoppingItemsFromDBAsList = new LinkedList(Arrays.asList(userSelectedShoppingItemsFromDB));
		
		//frequentItemSetGenerator.updateFrequentItemSets(userSelectedShoppingItemsFromDBAsList.toArray(new UserSelectedShoppingItems[0]));
		
		//FrequentItemSets<Item> frequentItemSets = frequentItemSetGenerator.getFrequentItemSets();
		
		associationRuleGenerator.updateAssociationRules(userSelectedShoppingItemsFromDBAsList.toArray(new UserSelectedShoppingItems[0]));
		
		RuleSet<Item> associationRules = associationRuleGenerator.getAssociationRules();
		
		PredictionAppConfig predictionAppConfig = App.getPredictionAppConfig();
		
		//FrequentlyPurchasedShoppingItemSet [] frequentlyPurchasedShoppingItemSets = FrequentlyPurchasedShoppingItemSetFactory.generateFrequentlyPurchasedShoppingItemSet(frequentItemSets);
		
		AssociationRuleForShoppingItems [] associationRulesForShoppingItemSets = AssociationRuleForShoppingItemsFactory.generateAssociationRulesForShoppingItemSets(associationRules);
		
		Map values = new HashMap();
    	values.put("ip", predictionAppConfig.getServerIP());
    	values.put("port", Integer.toString(predictionAppConfig.getPortToRunServerOn()));
    	//values.put("frequentItemSets", frequentlyPurchasedShoppingItemSets);
		values.put("associationRules", associationRulesForShoppingItemSets);
    	
    	try {
			String htmlFilledIn = predictionAppConfig.getHtmlTemplatingEngineInstance().generateTemplatedHTML("ItemPredictions.html", values);
			return htmlFilledIn;
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Internal server error";
		}
	};
	
	public static void setRouteToRespondToGetterRequest(String serverPath, Route routeToRespond) {
		get(serverPath, routeToRespond);
	}
	
	public static void setRouteToRespondToGetterRequest(String serverPath, String dataTypeToAccept, Route routeToRespond) {
		get(serverPath, dataTypeToAccept, routeToRespond);
	}

}
