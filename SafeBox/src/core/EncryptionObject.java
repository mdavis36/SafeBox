package core;

class EncryptionObject {
	private byte[] iv;
	private byte[] data;

	/**
	 * @param data the data in the encrypted object
	 * @param iv the initialization variable assigned to the object
	 */
	protected EncryptionObject(byte[] data, byte[] iv) {
		this.iv = iv;
		this.data = data;
	}

	protected byte[] getIv() {
		return iv;
	}

	protected void setIv(byte[] iv) {
		this.iv = iv;
	}

	protected byte[] getData() {
		return data;
	}

	protected void setData(byte[] data) {
		this.data = data;
	}
}
