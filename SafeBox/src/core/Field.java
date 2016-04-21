package core;

import java.io.Serializable;

public class Field  implements Serializable{
	private static final long serialVersionUID = 1452217924276409260L;
	private String name;
	private String data;
	
	public Field() {
		name = "";
		data = "";
	}
	
	/**
	 * @param name the string being set as the name of the field
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param data the string being set as the data of the field
	 */
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
