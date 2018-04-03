package MongoDBDocumentProcessingTests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import spark.utils.Assert;

class RandomStringGeneratorTest {
	@Test
	void testForLackOfRepetitionInRandomStringGeneratorValues() {
		for (int i = 0; i<10; ++i) {
			HashSet<String> returnedValues = new HashSet<>();
			
			RandomStringGenerator randomStringGenerator = new RandomStringGenerator(i+"");
			
			for (int j = 0; j < 1000; ++j) {
				String stringCreatedByGenerator = randomStringGenerator.getRandomString();
				Assert.isTrue(!returnedValues.contains(stringCreatedByGenerator), "Duplicate value returned by random string generator after "+j+"iterations");
				returnedValues.add(stringCreatedByGenerator);
			}
		}
	}

}
