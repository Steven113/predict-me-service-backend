package FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

	private static String eolString = System.lineSeparator();
	
	static String extractFileNameFromPath(String path) {
		return (new File(path)).getName();
	}
	
	public static List<String> getFileContentsAsListOfStrings(String pathString) throws IOException {
		Path pathToFile = Paths.get(pathString);
		
		List<String> linesOfFile = Files.readAllLines(pathToFile);
		
		return linesOfFile;
	}
	
	public static String getFileContentsAsString(String pathString) throws IOException {
		
		List<String> linesOfFile = getFileContentsAsListOfStrings(pathString);
		
		String fileContents = "";
		
		for (int lineNo = 0; lineNo < linesOfFile.size(); ++lineNo) {
			fileContents = fileContents + linesOfFile.get(lineNo);
			
			if (lineNo < linesOfFile.size()-1) {
				fileContents = fileContents + eolString;
			}
		}
		
		return fileContents;
	}

}
