import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import freemarker.template.TemplateException;
import spark.Route;

public class AppRoutes {

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
		
		userProvidedItems.add(req.queryParams("ShoppingItem1"));
		userProvidedItems.add(req.queryParams("ShoppingItem2"));
		userProvidedItems.add(req.queryParams("ShoppingItem3"));
		userProvidedItems.add(req.queryParams("ShoppingItem4"));
		userProvidedItems.add(req.queryParams("ShoppingItem5"));
		
		UserSelectedShoppingItems userSelectedShoppingItems = new UserSelectedShoppingItems(userProvidedItems);
		
		App.putObjectInMongoDBCollection(userSelectedShoppingItems);
		
		return "Data submitted";
	};
	
	public static void setRouteToRespondToGetterRequest(String serverPath, Route routeToRespond) {
		get(serverPath, routeToRespond);
	}
	
	public static void setRouteToRespondToGetterRequest(String serverPath, String dataTypeToAccept, Route routeToRespond) {
		get(serverPath, dataTypeToAccept, routeToRespond);
	}

}
