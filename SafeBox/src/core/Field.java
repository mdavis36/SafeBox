package core;

public class Field {
	private String name;
	private String data;
	
	public Field() {
		name = "";
		data = "";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public String getData() {
		return data;
	}
}
