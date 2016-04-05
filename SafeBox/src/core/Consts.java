package core;

public class Consts {
	public static final String ENCRYPTED_FILE_NAME = "EncryptedData.txt";
	public static final String PLAIN_FILE_NAME = "PlainData.ser";
	
	public static final String HINT_NAME = "hint";
	public static final String IV_PDM_ENTRY_NAME = "EFSM_IV"; // name of the IV entry in PlainDataManager
	
	public static final byte[] SALT = "CHANGE THIS!!!".getBytes(); // change this sometime
}
