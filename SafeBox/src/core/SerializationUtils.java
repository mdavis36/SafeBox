package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

class SerializationUtils {
	protected static Object byteArrayToObject(byte[] bytes) {
		Object tmpObject = null;
		try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes); ObjectInput objectInput = new ObjectInputStream(byteStream)) {
			tmpObject = objectInput.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmpObject;
	}

	/**
	 * @param obj the object being turned to a byte array
	 * @return the byte array
	 */
	protected static byte[] objectToByteArray(Object obj) {
		byte[] byteArray = null;
		try (ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream()) {
			new ObjectOutputStream(byteArrayOS).writeObject(obj);
			byteArray = byteArrayOS.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArray;
	}

	public static void main(String[] args) {
		HashMap<String, String> testHashMap = new HashMap<String, String>(); // set up a new hash map, put a few test elements in it
		testHashMap.put("test key #1", "test value #1");
		testHashMap.put("test key #2", "test value #2");

		byte[] testHashMapSerialized = objectToByteArray(testHashMap); // Serialize it. At this point, we could write it to a text file.

		testHashMap.clear(); // Kill the original hash map. This is what would happen when we close the program
		testHashMap = null;

		Object reconstructedHashMap = byteArrayToObject(testHashMapSerialized); // reconstruct the object from the byte array (which could have been read from a text file)

		if (reconstructedHashMap instanceof HashMap) { // make sure the object is a HashMap
			System.out.println(reconstructedHashMap.toString()); // do something with it
		} else {
			System.out.println("Error: unserialized object isn't a HashMap");
		}
	}
}
