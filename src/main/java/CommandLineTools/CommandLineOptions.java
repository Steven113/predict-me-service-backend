package CommandLineTools;
import java.util.HashMap;
import java.util.LinkedList;

public class CommandLineOptions {
	HashMap<String, LinkedList<String>> parsedCommandLineOptions = new HashMap<String, LinkedList<String>>();
	
	public CommandLineOptions(String [] args) {
		parsedCommandLineOptions = CommandLineOptionsParser.parseCommandLineOptions(args);

	}
	
	public CommandLineOptions(String args) {
		parsedCommandLineOptions = CommandLineOptionsParser.parseCommandLineOptions(args);

	}
	
	public CommandLineOptions(String args, String delimiter) {
		parsedCommandLineOptions = CommandLineOptionsParser.parseCommandLineOptions(args, delimiter);

	}
	
	public boolean hasParameter(String parameter) {
		return parsedCommandLineOptions.containsKey(parameter);
	}
	
	public boolean hasParameterWithKAttributes(String parameter, int k) {
		return parsedCommandLineOptions.containsKey(parameter) && parsedCommandLineOptions.get(parameter).size()==k;
	}
	
	public LinkedList<String> getValueOfParameter(String parameter) {
		return new LinkedList<>(parsedCommandLineOptions.get(parameter));
	}
}
