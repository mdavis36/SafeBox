package core;
import java.util.ArrayList;

public class Record extends Folder{
	private static final long serialVersionUID = 6005440839659159341L;
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	public Record() {
		//check
	}
	
	public boolean addField(Field field) {
		fields.add(field);
		return true;
	}
	
	//TO-DO test if it will delete same object
	public void deleteField(Field field) {
		fields.remove(field);
	}
	
	public Field getField(int field) {
		return fields.get(field);
	}
	
	public Field getField(String field) {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getName() == field) {
				return fields.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}
	
	public void swap(int field1, int field2) {
		Field temp = fields.set(field1, fields.get(field2));
		fields.set(field2, temp);
	}
	
	public String toString(){
		String output = name + "\n";
		int length = fields.size();
		for(int i = 0; i < length; i++){
			output += fields.get(i).getName();
			output +="\n";
			output += fields.get(i).getData();
			output += "\n";
		}
		return output;
	}
}
