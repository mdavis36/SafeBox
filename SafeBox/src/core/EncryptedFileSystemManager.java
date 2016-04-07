package core;

import javax.crypto.BadPaddingException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class EncryptedFileSystemManager {
	FileSystemHandler fileSystem;
	char[] password;
	
	public EncryptedFileSystemManager() {
		// TODO Auto-generated constructor stub
		fileSystem = new FileSystemHandler();
	}
	
	/**
	 * @return the current FileSystemHandler
	 */
	public FileSystemHandler getFileSystemHandler(){
		return fileSystem;
	}
	
	/**
	 * @param password the password to be used for future encryption/decryption
	 */
	public void setPassword(char[] password){
		this.password = password;
	}
	
	/**
	 * @return true if a FileSystemHandler has been saved before, otherwise false
	 */
	public boolean fileSystemExists(){
		return FileSystemManager.fileExists(Consts.ENCRYPTED_FILE_NAME);
	}
	
	/**
	 * Creates a new empty FileSystemHandler and makes it the current fileSystem
	 */
	public void initNewFileSystem(){
		fileSystem = new FileSystemHandler();
	}
	
	/**
	 * Attempts to read and decrypt the serialized file system.
	 * @return true if successful, otherwise false
	 */
	public boolean loadFileSystemHandler(){		
		byte[] savedData = FileSystemManager.readFromFile(Consts.ENCRYPTED_FILE_NAME);
		byte[] IV = getIv();
		
		byte[] decryptedSavedData;
		
		try {
			decryptedSavedData = DataEncryptor.decryptData(new EncryptionObject(savedData, IV), this.password, Consts.SALT);
		} catch (BadPaddingException ex){ // wrong password
			return false;
		} catch (Exception ex){
			ex.printStackTrace();
			return false; // something didn't work...
		}
		
		fileSystem = (FileSystemHandler) SerializationUtils.byteArrayToObject(decryptedSavedData);
		
		return true;
	}
	
	/**
	 * Attempts to encrypt and save the serialized file system.
	 * @return true if successful, otherwise false
	 */
	public boolean saveFileSystemHandler(){
		EncryptionObject encryptedFileSystem;
		try {
			encryptedFileSystem = DataEncryptor.encryptData(SerializationUtils.objectToByteArray(fileSystem), password, Consts.SALT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		storeIv(encryptedFileSystem.getIv());
		FileSystemManager.saveToFile(encryptedFileSystem.getData(), Consts.ENCRYPTED_FILE_NAME);
		
		return true;
	}
	
	/**
	 * Stores the IV object in PlainDataManager
	 * @param iv the new IV to save
	 */
	private void storeIv(byte[] iv){
		PlainDataManager.setElement(Consts.IV_PDM_ENTRY_NAME, MiscUtils.bytesToBase64String(iv));
		PlainDataManager.saveToFile();
	}
	
	/**
	 * Attempts to load an IV from PlainDataManager
	 * @return an IV if saved, otherwise null
	 */
	private byte[] getIv(){
		try {
			return Base64.decode(PlainDataManager.getElement(Consts.IV_PDM_ENTRY_NAME));
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		EncryptedFileSystemManager eFSM = new EncryptedFileSystemManager();
		
		eFSM.setPassword("this is my password!".toCharArray()); // this is used for all encryption/decryption
		
		if (!eFSM.fileSystemExists()){ // if this is the first run
			System.out.println("No filesystem exists, creating one!");
			eFSM.initNewFileSystem(); // make a blank filesystem
		} else {
			if (eFSM.loadFileSystemHandler()){ // try to load the saved filesystem
				System.out.println("Successfully loaded FSH!");
			} else {
				System.out.println("Error loading FSH!");
				return; // abort if loading failed
			}
		}
		
		FileSystemHandler fsh = eFSM.getFileSystemHandler(); // this gets the actual saved FileSystemhandler object
		
		
		System.out.println(fsh);
		
		fsh.createFolder(fsh.getRoot(), "test folder"); // change something here
		
		System.out.println(fsh); // write it out to see the change
		
		eFSM.saveFileSystemHandler(); // save the FileSystemHandler to the disk
		
		
	}
	
}
