package CommandLineTools;
import java.util.HashMap;
import java.util.LinkedList;

public class ServerCommandLineOptionsValidator implements ICommandLineOptionsValidator {

	final static String [] requiredKeys = new String[] {"-port"};
	
	@Override
	public boolean checkIfCommandLineOptionsAreValid(HashMap<String, LinkedList<String>> commandLineOptionsParsed) {
		for (String key : requiredKeys) {
			if (!commandLineOptionsParsed.containsKey(key) || !(commandLineOptionsParsed.get(key).size()==1)) {
				return false;
			}
		}
		
		return true;
	}


}
