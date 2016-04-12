package core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PlainDataManager {
	static HashMap<String, String> data = null;
	static byte[] dataSerialized;
	
	private static void attemptToLoadFromFile(){
		if (FileSystemManager.fileExists(Consts.PLAIN_FILE_NAME)){
			loadFromFile();
		}
		if (data == null){
			data = new HashMap<String, String>();
		}
	}
	
	protected static String getElement(String element) {
		if (data == null) {
			attemptToLoadFromFile();
		}
		return (String) data.get(element);
	}
	
	protected static void setElement(String element, String value) {
		if (data == null) {
			attemptToLoadFromFile();
		}
		data.put(element, value);
	}
	
	protected static void saveToFile() {
		dataSerialized = SerializationUtils.objectToByteArray(data);
		FileSystemManager.saveToFile(dataSerialized, Consts.PLAIN_FILE_NAME);
	}
	
	private static void loadFromFile() {
		dataSerialized = FileSystemManager.readFromFile(Consts.PLAIN_FILE_NAME);
		data = (HashMap<String, String>) SerializationUtils.byteArrayToObject(dataSerialized);
	}
	
	public static void main(String[] args){
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


