import static spark.Spark.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateException;

public class Main {
	
	static CommandLineOptions commandLineOptions;
	
	static String serverIP = "";
	
	static int portToRunServerOn = 7000;
	
	static HTMLTemplatingEngine htmlTemplatingEngineInstance;
	
	static AllowedShoppingItemList allowedShoppingItemList;
	
    public static void main(String[] args) {
    	commandLineOptions = new CommandLineOptions(args);
    	
    	if (!validateCommandLineArguments()) {
    		return;
    	}
    	
    	extractDataFromCommandLineArguments();
    	
    	
    	staticFiles.location("/public");
    	staticFiles.expireTime(600L);
    	
    	port(portToRunServerOn);
    	
    	Map values = new HashMap();
    	values.put("name", "steven");
    	values.put("ip", serverIP);
    	values.put("port", Integer.toString(portToRunServerOn));
    	values.put("allowedShoppingItemList", allowedShoppingItemList.getAllAllowedShoppingItems());
    	
    	try {
			String htmlFilledIn = htmlTemplatingEngineInstance.generateTemplatedHTML("index.html", values);
			get(ServerURLs.Web.DEBUG_PAGE, (req, res) -> htmlFilledIn);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        get(ServerURLs.Web.USER_DATA_SUBMIT_PAGE, "application/json", (req,res) -> "Data submitted");
    }

	private static void extractDataFromCommandLineArguments() {
    	portToRunServerOn = Integer.parseInt(commandLineOptions.getValueOfParameter("port").get(0));
    	
    	serverIP = (commandLineOptions.getValueOfParameter("serverIP").get(0));
    	
    	htmlTemplatingEngineInstance = new HTMLTemplatingEngine(commandLineOptions.getValueOfParameter("pathToTemplateDirectory").get(0));
    	
    	try {
			allowedShoppingItemList = AllowedShoppingItemListFactory.generateAllowedShoppingItemListFromFile(commandLineOptions.getValueOfParameter("pathToAllowedShoppingItems").get(0));
		} catch (IOException e) {
			System.out.println("Error when reading file with allowed shopping items.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private static boolean validateCommandLineArguments() {
		if (!commandLineOptions.hasParameterWithKAttributes("port", 1)) {
			System.out.println("You have not specified the port number to run the server on. Please add the argument -port [port number]");
    		return false;
    	}
    	
    	if (!commandLineOptions.hasParameterWithKAttributes("serverIP", 1)) {
    		System.out.println("You have not specified the IP of the server. Please add the argument -serverIP [server IP]");
    		return false;
    	}
    	
    	if (!commandLineOptions.hasParameterWithKAttributes("pathToTemplateDirectory", 1)) {
    		System.out.println("You have not specified the path to the directory where the Freemarker templates are kept. Please add the argument -pathToTemplateDirectory [path to Freemarker templates]");
    		return false;
    	}
    	
    	if(!commandLineOptions.hasParameterWithKAttributes("pathToAllowedShoppingItems", 1)) {
    		System.out.println("You have not specified the path to the file with the allowed shopping items. Please add the argument -pathToAllowedShoppingItems [path to file with allowed shopping items]");
    		return false;
    	}
    	
    	return true;
	}
}