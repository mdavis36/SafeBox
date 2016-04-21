package core;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StorageManager {

	/**
	 * @param data bytes being saved
	 * @param file the name of the file being saved to
	 * @return whether or not it was saved
	 */
	protected static boolean saveToFile(byte[] data, String file) {
		try {
			Files.write(new File(file).toPath(), data);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param file the name of the file bing read from
	 * @return the bytes read from the file
	 */
	protected static byte[] readFromFile(String file) {
		try {
			return Files.readAllBytes(Paths.get(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param file the name of the file
	 * @return whether the file exists
	 */
	protected static boolean fileExists(String file) {
		if (file == null){
			return false;
		}
		
		return new File(file).exists();
	}
}
