import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateException;
import spark.Route;

public class AppRoutes {

	public static Route userShoppingListFormPage = (req, res) -> {
		PredictionAppConfig predictionAppConfig = Main.getPredictionAppConfig();
		
		Map values = new HashMap();
    	values.put("name", "steven");
    	values.put("ip", predictionAppConfig.getServerIP());
    	values.put("port", Integer.toString(predictionAppConfig.getPortToRunServerOn()));
    	values.put("allowedShoppingItemList", predictionAppConfig.getAllowedShoppingItemList().getAllAllowedShoppingItems());
    	
    	try {
			String htmlFilledIn = predictionAppConfig.getHtmlTemplatingEngineInstance().generateTemplatedHTML("index.html", values);
			return htmlFilledIn;
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "Internal server error";
		}
	};
	
	public static Route dataSubmissionPage = (req,res) -> "Data submitted";

}
