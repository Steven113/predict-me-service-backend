package CommandLineTools;
import java.util.HashMap;
import java.util.LinkedList;

public interface ICommandLineOptionsValidator {
	boolean checkIfCommandLineOptionsAreValid(HashMap<String, LinkedList<String>> commandLineOptionsParsed);
}
