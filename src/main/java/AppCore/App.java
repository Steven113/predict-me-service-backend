package AppCore;
import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import DataMining.MongoDBDocumentCompatibleObject;
import DataMining.MongoDBManager;
import freemarker.template.TemplateException;

public class App {
	
	private static PredictionAppConfig predictionAppConfig = null;
	
	private static MongoDBManager mongoDBConnector = null;
	
    public static void main(String[] args) {
    	
    	/*
    	 * Generate configuration information from command line arguments
    	 */
    	predictionAppConfig = new PredictionAppConfig(args);
    	
    	/*
    	 * Check configuration occurred correctly, terminate program if
    	 * the config failed
    	 */
    	if (!predictionAppConfig.appArgsAreValid()) {
    		System.out.println("Error generated when configuring app.");
    		return;
    	} else {
    		System.out.println("App config successful");
    	}
    	
    	//get connection to mongodb instance
    	mongoDBConnector = new MongoDBManager(predictionAppConfig.getMongoDBHost(), predictionAppConfig.getMongoDBPort(), predictionAppConfig.getMongodbDatabaseName());
    	
    	//set up public folder for retrieving resources from server
    	staticFiles.location("/public");
    	staticFiles.expireTime(600L);
    	
    	port(predictionAppConfig.getPortToRunServerOn());
    	
    	AppRoutes.setRouteToRespondToGetterRequest(ServerURLs.Web.WELCOME_PAGE, AppRoutes.userShoppingListFormPage);
    	
        AppRoutes.setRouteToRespondToGetterRequest(ServerURLs.Web.USER_DATA_SUBMIT_PAGE, "application/json", AppRoutes.dataSubmissionPage);
    }

	public static PredictionAppConfig getPredictionAppConfig() {
		return predictionAppConfig;
	}

	public static MongoDBManager getMongoDBConnector() {
		return mongoDBConnector;
	}
	
	public static void putObjectInMongoDBCollection(MongoDBDocumentCompatibleObject object) {
		mongoDBConnector.insertObjectIntoCollectionAsObject(predictionAppConfig.getMongodbCollectionName(), object);
	}
	
	public static Document [] getAllDocumentsFromMongoDBCollection() {
		return mongoDBConnector.getAllDocumentsFromCollection(predictionAppConfig.getMongodbCollectionName());
	}
	

	
}