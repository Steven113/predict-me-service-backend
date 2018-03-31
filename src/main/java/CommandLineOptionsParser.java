import java.util.HashMap;
import java.util.LinkedList;

public class CommandLineOptionsParser {
	
	/**
	 * Extracts a mapping of command line args from an array of strings.
	 * This version is meant to be used with the args array passed
	 * in main
	 * @param args
	 * @return
	 */
	public static HashMap<String, LinkedList<String>> parseCommandLineOptions(String [] args){
		HashMap<String, LinkedList<String>> parsedOptionMapping = new HashMap<String, LinkedList<String>>();
		
		for (int index = 0; index<args.length; ++index) {
			if (args[index].startsWith("-")) {
				String optionVariableName = (args[index]).replaceFirst("-", "");
				
				parsedOptionMapping.put(optionVariableName, new LinkedList<String>());
				
				for (index = index + 1; index < args.length && !args[index].startsWith("-"); ++index) {
					parsedOptionMapping.get(optionVariableName).add(new String(args[index]));
				}
				--index;
			}
		}
		
		return parsedOptionMapping;
	}
	
	/**
	 * Extracts a mapping of command line parameters to the values provided
	 * by the user in the form of a string. Allows a arbitrary delimiter
	 * @param argumentString
	 * @param delimiter
	 * @return
	 */
	public static HashMap<String, LinkedList<String>> parseCommandLineOptions(String argumentString, String delimiter){
		return parseCommandLineOptions(argumentString.split(delimiter));
	}
	
	/**
	 * Extracts a mapping of command line parameters to the values provided
	 * by the user in the form of a string. It is assumed that a single space 
	 * will be the delimiter
	 * @param args
	 * @return
	 */
	
	public static HashMap<String, LinkedList<String>> parseCommandLineOptions(String argumentString){
		return parseCommandLineOptions(argumentString, " ");
	}
}
