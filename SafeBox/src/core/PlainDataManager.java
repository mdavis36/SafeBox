package core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PlainDataManager {
	static HashMap<String, String> data = new HashMap<String, String>();
	static byte[] dataSerialized;
	
	protected static String getElement(String element) {
		if (data == null) {
			loadFromFile();
			if (data == null) {
				return null;
			}
		}
		return (String) data.get(element);
	}
	
	protected static void setElement(String element, String value) {
		data.put(element, value);
	}
	
	protected static void saveToFile() {
		dataSerialized = SerializationUtils.objectToByteArray(data);
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(Consts.PLAIN_FILE_NAME);
		} catch (FileNotFoundException e) {
			System.out.println("file does not exist");
		}
		try {
			file.write(dataSerialized);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("couldnt write data to file");
		}
		
	}
	
	private static void loadFromFile() {
		FileInputStream file = null;
		try {
			file = new FileInputStream(Consts.PLAIN_FILE_NAME);
		} catch (FileNotFoundException e) {
			System.out.println("file does not exist");
		}
		try {
			file.read(dataSerialized);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("couldnt write data to file");
		}
		data = (HashMap<String, String>) SerializationUtils.byteArrayToObject(dataSerialized);
	
		System.out.println(data.toString());
	}
	
	public static void main(String[] args){
		setElement("test key #1", "test value #1");
		setElement("test key #2", "test value #2");
		
		saveToFile();
		data.clear();
		data = null;
		loadFromFile();
		
		System.out.println(getElement("test key #1"));
		System.out.println();
		
	}
}


