package core;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author
 *
 */
class DataEncryptor {
	private static final int ITERATION_COUNT = 65536;
	private static final int KEY_LENGTH = 128;
	private static final String ALGORITHM = "AES";
	private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";

	private static SecretKey getKey(SecretKeyFactory factory, char[] password, byte[] salt) throws InvalidKeySpecException {
		KeySpec keySpec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
		SecretKey secretKey = factory.generateSecret(keySpec);

		return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
	}

	private static EncryptionObject encryptDataFromKey(SecretKey secretKey, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		AlgorithmParameters params = cipher.getParameters();
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();

		return new EncryptionObject(cipher.doFinal(data), iv);
	}

	private static byte[] decryptDataFromKey(SecretKey secretKey, EncryptionObject encryptedObject) throws Exception {
		Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(encryptedObject.getIv()));

		return cipher.doFinal(encryptedObject.getData());
	}

	private static SecretKeyFactory getFactory() throws NoSuchAlgorithmException {
		return SecretKeyFactory.getInstance(KEY_ALGORITHM);
	}

	protected static EncryptionObject encryptData(byte[] data, char[] password, byte[] salt) throws Exception {
		SecretKey key = getKey(getFactory(), password, salt);

		return encryptDataFromKey(key, data);
	}

	/**
	 * @param data the object that was previously encrypted by dataEncryptor
	 * @param password the password used to encrypt
	 * @param salt the salt used to encrypt the data
	 * @return the decrypted data
	 * @throws Exception if an error occurred decrypting
	 */
	protected static byte[] decryptData(EncryptionObject data, char[] password, byte[] salt) throws Exception {
		SecretKey key = getKey(getFactory(), password, salt);

		return decryptDataFromKey(key, data);
	}
}
