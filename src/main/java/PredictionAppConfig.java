import java.io.IOException;

import CommandLineTools.CommandLineOptions;

public class PredictionAppConfig {
	
	private CommandLineOptions commandLineOptions;
	
	private String serverIP = "";
	
	private int portToRunServerOn = 7000;
	
	private HTMLTemplatingEngine htmlTemplatingEngineInstance;
	
	private AllowedShoppingItemList allowedShoppingItemList;
	
	private boolean argsAreValid = true;
	
	private String mongoDBHost = "";
	
	private int mongoDBPort = 0;
	
	private String mongodbDatabaseName = "";
	
	private String mongodbCollectionName = "";
	
	public PredictionAppConfig(String [] commandLineArgs) {
		commandLineOptions = new CommandLineOptions(commandLineArgs);
    	
    	if (!validateCommandLineArguments()) {
    		argsAreValid = false;
    		return;
    	} else {
    		argsAreValid = true;
    	}
    	
    	extractDataFromCommandLineArguments();
	}
	
	
	private void extractDataFromCommandLineArguments() {
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
    	
    	mongoDBPort = Integer.parseInt(commandLineOptions.getValueOfParameter("mongodbport").get(0));
    	
    	mongoDBHost = (commandLineOptions.getValueOfParameter("mongodbhost").get(0));
    	
    	mongodbDatabaseName = (commandLineOptions.getValueOfParameter("mongodbDatabaseName").get(0));
    	
    	mongodbCollectionName = (commandLineOptions.getValueOfParameter("mongodbCollectionName").get(0));
	}
	
	private boolean validateCommandLineArguments() {
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
    	
    	if(!commandLineOptions.hasParameterWithKAttributes("mongodbhost", 1)) {
    		System.out.println("You have not specified the hostname of the mongodb server. Please add the argument -mongodbhost [hostname for mongodb server]");
    		return false;
    	}
    	
    	if(!commandLineOptions.hasParameterWithKAttributes("mongodbport", 1)) {
    		System.out.println("You have not specified the port the mongodb server is running on. Please add the argument -mongodbport [port mongodb server is running on]");
    		return false;
    	}
    	
    	if(!commandLineOptions.hasParameterWithKAttributes("mongodbDatabaseName", 1)) {
    		System.out.println("You have not specified the name of the mongodb database. Please add the argument -mongodbDatabaseName [mongodb database name]");
    		return false;
    	}
    	
    	if(!commandLineOptions.hasParameterWithKAttributes("mongodbCollectionName", 1)) {
    		System.out.println("You have not specified the name of the collection to use from the mongodb database. Please add the argument -mongodbCollectionName [mongodb collection name]");
    		return false;
    	}
    	
    	return true;
	}


	public CommandLineOptions getCommandLineOptions() {
		return commandLineOptions;
	}


	public String getServerIP() {
		return serverIP;
	}


	public int getPortToRunServerOn() {
		return portToRunServerOn;
	}


	public HTMLTemplatingEngine getHtmlTemplatingEngineInstance() {
		return htmlTemplatingEngineInstance;
	}

	

	public AllowedShoppingItemList getAllowedShoppingItemList() {
		return allowedShoppingItemList;
	}


	public boolean appArgsAreValid() {
		return argsAreValid;
	}


	public String getMongoDBHost() {
		return mongoDBHost;
	}


	public int getMongoDBPort() {
		return mongoDBPort;
	}


	public String getMongodbDatabaseName() {
		return mongodbDatabaseName;
	}


	public String getMongodbCollectionName() {
		return mongodbCollectionName;
	}
	
	
}
