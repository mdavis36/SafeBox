package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
}
