package core;

import java.util.HashMap;

public class PlainDataManager {
	static HashMap<String, String> data = null;
	static byte[] dataSerialized;

	private static void attemptToLoadFromFile() {
		if (StorageManager.fileExists(Consts.PLAIN_FILE_NAME)) {
			loadFromFile();
		}
		if (data == null) {
			data = new HashMap<String, String>();
		}
	}

	/**
	 * @param element the index being grabbed from the hashmap
	 * @return the data at the element
	 */
	protected static String getElement(String element) {
		if (data == null) {
			attemptToLoadFromFile();
		}
		return data.get(element);
	}

	/**
	 * @param element the string index for the hashmap
	 * @param value hte data being saved at the index in the hashmap
	 */
	protected static void setElement(String element, String value) {
		if (data == null) {
			attemptToLoadFromFile();
		}
		data.put(element, value);
	}

	protected static void saveToFile() {
		dataSerialized = SerializationUtils.objectToByteArray(data);
		StorageManager.saveToFile(dataSerialized, Consts.PLAIN_FILE_NAME);
	}

	@SuppressWarnings("unchecked")
	private static void loadFromFile() {
		dataSerialized = StorageManager.readFromFile(Consts.PLAIN_FILE_NAME);
		data = (HashMap<String, String>) SerializationUtils.byteArrayToObject(dataSerialized);
	}

	public static void main(String[] args) {
		setElement("test key #1", "test value #1");
		setElement("test key #2", "test value #2");

		saveToFile();
		data.clear();
		data = null;
		loadFromFile();

		System.out.println(getElement("test key #2"));
		System.out.println();

	}
}
