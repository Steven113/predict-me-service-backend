package CommandLineTools;

public class ValidatedCommandLineOptions extends CommandLineOptions {
	ICommandLineOptionsValidator commandLineOptionsValidator;
	
	public ValidatedCommandLineOptions(String[] args, ICommandLineOptionsValidator commandLineOptionsValidator) {
		super(args);
		this.commandLineOptionsValidator = commandLineOptionsValidator;
	}

	public ValidatedCommandLineOptions(String args, ICommandLineOptionsValidator commandLineOptionsValidator) {
		super(args);
		this.commandLineOptionsValidator = commandLineOptionsValidator;
	}

	public ValidatedCommandLineOptions(String args, String delimiter, ICommandLineOptionsValidator commandLineOptionsValidator) {
		super(args, delimiter);
		this.commandLineOptionsValidator = commandLineOptionsValidator;
	}
	
	public boolean isValid() {
		return commandLineOptionsValidator.checkIfCommandLineOptionsAreValid(parsedCommandLineOptions);
	}

}
