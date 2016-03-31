package core;

public class EncryptionObject {
	private byte[] iv;
	private byte[] data;
	
	public EncryptionObject(byte[] data, byte[] iv) {
		this.iv = iv;
		this.data = data;
	}
	
	public byte[] getIv() {
		return iv;
	}
	public void setIv(byte[] iv) {
		this.iv = iv;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
}
