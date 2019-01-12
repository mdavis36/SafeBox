package core;

import java.util.Arrays;

import javax.crypto.BadPaddingException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class EncryptedStorageManager {
	FileSystemHandler fileSystem;
	char[] password;

	public EncryptedStorageManager() {
		fileSystem = new FileSystemHandler();
	}

	/**
	 * @return the current FileSystemHandler
	 */
	public FileSystemHandler getFileSystemHandler() {
		return fileSystem;
	}

	/**
	 * @param password the password to be used for future encryption/decryption
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}

	/**
	 * @return true if a FileSystemHandler has been saved before, otherwise false
	 */
	public boolean fileSystemExists() {
		return StorageManager.fileExists(Consts.ENCRYPTED_FILE_NAME);
	}

	/**
	 * Creates a new empty FileSystemHandler and makes it the current fileSystem
	 */
	public void initNewFileSystem() {
		fileSystem = new FileSystemHandler();
	}

	/**
	 * @param passwordToTest
	 * @return true if passwordToTest equals the current password, false otherwise. Note that the current password is the password in memory, NOT the password on the filesystem
	 */
	public boolean isCurrentPassword(char[] passwordToTest) {
		return Arrays.equals(this.password, passwordToTest);
	}

	/**
	 * @param password password to check
	 * @return true if password meets minimum requirements, false otherwise
	 */
	public boolean passwordMeetsRequirements(char[] password) {
		if (password.length > 6) {
			return true;
		}
		return false;
	}

	/**
	 * Attempts to read and decrypt the serialized file system.
	 * @return true if successful, otherwise false
	 */
	public boolean loadFileSystemHandler() {
		byte[] savedData = StorageManager.readFromFile(Consts.ENCRYPTED_FILE_NAME);
		byte[] IV = getIv();

		byte[] decryptedSavedData;

		try {
			decryptedSavedData = DataEncryptor.decryptData(new EncryptionObject(savedData, IV), this.password, Consts.SALT);
		} catch (BadPaddingException ex) { // wrong password
			return false;
		} catch (Exception ex) {
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
	public boolean saveFileSystemHandler() {
		EncryptionObject encryptedFileSystem;
		try {
			encryptedFileSystem = DataEncryptor.encryptData(SerializationUtils.objectToByteArray(fileSystem), password, Consts.SALT);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		storeIv(encryptedFileSystem.getIv());
		StorageManager.saveToFile(encryptedFileSystem.getData(), Consts.ENCRYPTED_FILE_NAME);

		return true;
	}

	/**
	 * Stores the IV object in PlainDataManager
	 * @param iv the new IV to save
	 */
	private void storeIv(byte[] iv) {
		PlainDataManager.setElement(Consts.IV_PDM_ENTRY_NAME, MiscUtils.bytesToBase64String(iv));
		PlainDataManager.saveToFile();
	}

	/**
	 * Attempts to load an IV from PlainDataManager
	 * @return an IV if saved, otherwise null
	 */
	private byte[] getIv() {
		try {
			return Base64.decode(PlainDataManager.getElement(Consts.IV_PDM_ENTRY_NAME));
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
