package core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;

class SerializationUtils {
	protected static Object byteArrayToObject(byte[] bytes){
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		ObjectInput objectInput = null;
		
		Object tmpObject = null;
		
		
		try {
		  objectInput = new ObjectInputStream(byteStream);
		  tmpObject = objectInput.readObject(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  try {
		    byteStream.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		  try {
		    if (objectInput != null) {
		      objectInput.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		return tmpObject;
	}
	
	protected static byte[] objectToByteArray(Object obj){
		ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
		ObjectOutput objectOutput = null;
		byte[] byteArray = null;
		try {
		  objectOutput = new ObjectOutputStream(byteArrayOS);   
		  objectOutput.writeObject(obj);
		  byteArray = byteArrayOS.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		  try {
		    if (objectOutput != null) {
		      objectOutput.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		  try {
		    byteArrayOS.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		return byteArray;
	}
	
	public static void main(String[] args){
		HashMap<String, String> testHashMap = new HashMap<String, String>(); // set up a new hash map, put a few test elements in it
		testHashMap.put("test key #1", "test value #1");
		testHashMap.put("test key #2", "test value #2");
		
		byte[] testHashMapSerialized = objectToByteArray(testHashMap); // Serialize it. At this point, we could write it to a text file.
		
		testHashMap.clear(); // Kill the original hash map. This is what would happen when we close the program 
		testHashMap = null;
		
		Object reconstructedHashMap = byteArrayToObject(testHashMapSerialized); // reconstruct the object from the byte array (which could have been read from a text file)
		
		if (reconstructedHashMap instanceof HashMap){ // make sure the object is a HashMap
			System.out.println(reconstructedHashMap.toString()); // do something with it
		} else {
			System.out.println("Error: unserialized object isn't a HashMap");
		}
		
		
		
	}
}
