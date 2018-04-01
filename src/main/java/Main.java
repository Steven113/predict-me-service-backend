import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateException;

public class Main {
	
	private static PredictionAppConfig predictionAppConfig = null;
	
    public static void main(String[] args) {
    	
    	predictionAppConfig = new PredictionAppConfig(args);
    	
    	if (!predictionAppConfig.appArgsAreValid()) {
    		System.out.println("Error generated when configuring app.");
    		return;
    	} else {
    		System.out.println("App config successful");
    	}
    	
    	staticFiles.location("/public");
    	staticFiles.expireTime(600L);
    	
    	port(predictionAppConfig.getPortToRunServerOn());
    	
    	get(ServerURLs.Web.WELCOME_PAGE, AppRoutes.userShoppingListFormPage);
    	
        get(ServerURLs.Web.USER_DATA_SUBMIT_PAGE, "application/json", AppRoutes.dataSubmissionPage);
    }

	public static PredictionAppConfig getPredictionAppConfig() {
		return predictionAppConfig;
	}

	
}