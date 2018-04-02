package AppCore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationUtils {

	public static byte [] serializeObjectToByteArray(Object objectToSerialize) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(objectToSerialize);
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			System.err.println("Error when serializing object");
			e.printStackTrace();
			return null;
		}
		
	}

}
