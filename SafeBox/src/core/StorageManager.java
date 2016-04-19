package core;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StorageManager {

	protected static boolean saveToFile(byte[] data, String file) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			return false;
		}
		try {
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				out.close();
			} catch (IOException d) {
				d.printStackTrace();
			}
			return false;
		}
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	protected static byte[] readFromFile(String file) {
		try {
			return Files.readAllBytes(Paths.get(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected static boolean fileExists(String file) {
		if (file == null){
			return false;
		}
		
		return new File(file).exists();
	}
}
