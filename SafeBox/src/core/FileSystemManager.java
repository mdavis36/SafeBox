package core;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;

public class FileSystemManager {

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
		
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			try {
				in.read(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	protected static byte[] readFromFile(String file) {
		FileInputStream input = null;
		try {
			byte[] data = new byte[1000];
			input = new FileInputStream(file);
			input.read(data);
			input.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected static boolean fileExists(String file) {
		try {
			FileInputStream in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
