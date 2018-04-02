import static spark.Spark.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.mrapp.apriori.FrequentItemSets;
import de.mrapp.apriori.Item;
import freemarker.template.TemplateException;
import spark.Route;

public class AppRoutes {
	
	private static UserSelectedShoppingItemsFactory userSelectedShoppingItemsFactory = new UserSelectedShoppingItemsFactory();
	
	private static FrequentItemSetGenerator frequentItemSetGenerator = new FrequentItemSetGenerator();
	
	public static Route userShoppingListFormPage = (req, res) -> {
		PredictionAppConfig predictionAppConfig = App.getPredictionAppConfig();
		
		Map values = new HashMap();
    	values.put("name", "steven");
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
		
		UserSelectedShoppingItems [] userSelectedShoppingItemsFromDB = userSelectedShoppingItemsFactory.getArrayOfUserSelectedShoppingItemsFromArrayOfDocuments(App.getAllDocumentsFromCollection());
		
		LinkedList<UserSelectedShoppingItems> userSelectedShoppingItemsFromDBAsList = new LinkedList(Arrays.asList(userSelectedShoppingItemsFromDB));
		
		frequentItemSetGenerator.updateFrequentItemSets(userSelectedShoppingItemsFromDBAsList);
		
		FrequentItemSets<Item> frequentItemSets = frequentItemSetGenerator.getFrequentItemSets();
		
		return "Data submitted";
	};
	
	public static void setRouteToRespondToGetterRequest(String serverPath, Route routeToRespond) {
		get(serverPath, routeToRespond);
	}
	
	public static void setRouteToRespondToGetterRequest(String serverPath, String dataTypeToAccept, Route routeToRespond) {
		get(serverPath, dataTypeToAccept, routeToRespond);
	}

}
