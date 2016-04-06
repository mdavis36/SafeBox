package core;

import java.io.Serializable;

public class Folder implements Serializable{
	private static final long serialVersionUID = -1433098247613371153L;
	private String name;
	
	public Folder() {
		name = "";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
