package core;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MiscUtils {
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	/**
	 * @param bytes the bytes of data being turned into a string
	 * @return the string form of the byte
	 */
	public static String bytesToBase64String(byte[] bytes){
		return new String(Base64.encode(bytes));
	}
	
	/**
	 * @param str the text being replaced
	 * @param times how many times str will be repeated in the string
	 * @return
	 */
	public static String repeatStr(String str, int times){
		return new String(new char[times]).replace("\0", str);
	}
	
}
