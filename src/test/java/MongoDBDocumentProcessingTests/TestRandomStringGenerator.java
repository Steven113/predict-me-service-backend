package MongoDBDocumentProcessingTests;

import java.util.HashSet;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRandomStringGenerator {
	/**
	 * Ensures that the random string generator does not generate any duplicates
	 * within a certain interval (no duplicates after generating X random strings)
	 */
	@Test
	public void testForLackOfRepetitionInRandomStringGeneratorValues() {
		for (int i = 0; i<10; ++i) {
			HashSet<String> returnedValues = new HashSet<>();
			
			RandomStringGenerator randomStringGenerator = new RandomStringGenerator(i+"");
			
			for (int j = 0; j < 1000; ++j) {
				String stringCreatedByGenerator = randomStringGenerator.getRandomString();
				assertTrue("Duplicate value returned by random string generator after "+j+"iterations", !returnedValues.contains(stringCreatedByGenerator));
				returnedValues.add(stringCreatedByGenerator);
			}
		}
	}

}
