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
    	
    	try {
			String htmlFilledIn = htmlTemplatingEngineInstance.generateTemplatedHTML("index.html", values);
			get(ServerURLs.Web.DEBUG_PAGE, (req, res) -> htmlFilledIn);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }

	private static void extractDataFromCommandLineArguments() {
    	portToRunServerOn = Integer.parseInt(commandLineOptions.getValueOfParameter("port").get(0));
    	
    	serverIP = (commandLineOptions.getValueOfParameter("serverIP").get(0));
    	
    	htmlTemplatingEngineInstance = new HTMLTemplatingEngine(commandLineOptions.getValueOfParameter("pathToTemplateDirectory").get(0));
	}
	
	private static boolean validateCommandLineArguments() {
		if (!commandLineOptions.hasParameterWithKAttributes("port", 1)) {
    		return false;
    	}
    	
    	if (!commandLineOptions.hasParameterWithKAttributes("serverIP", 1)) {
    		return false;
    	}
    	
    	if (!commandLineOptions.hasParameterWithKAttributes("pathToTemplateDirectory", 1)) {
    		return false;
    	}
    	
    	return true;
	}
}