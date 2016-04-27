package core;
import java.util.ArrayList;

public class Record extends Folder{
	private static final long serialVersionUID = 6005440839659159341L;
	private ArrayList<Field> fields = new ArrayList<Field>();
	
	public Record() {
		
	}
	
	public boolean addField(Field field) {
		fields.add(field);
		return true;
	}
	
	//TO-DO test if it will delete same object
	/**
	 * @param field the field being removed
	 */
	public void deleteField(int index) {
		fields.remove(index);
	}
	
	public Field getField(int field) {
		return fields.get(field);
		
	}
	
	public Field getField(String field) {
		for(Field theField : fields) {
			if (theField.getName().equals(field)) {
				return theField;
			}
		}
		
		return null;
	}
	
	public ArrayList<Field> getFields() {
		return fields;
	}
	
	/**
	 * @param field1 a field being switched
	 * @param field2 a field being switched
	 */
	public void swap(int field1, int field2) {
		Field temp = fields.set(field1, fields.get(field2));
		fields.set(field2, temp);
	}
	
	public String toString(){
		String output = "\n" + getName();
		int length = fields.size();
		for(int i = 0; i < length; i++){
			output += "\n\t"+fields.get(i).getName() + ":\n\t  " + getField(i).getData();
		}
		return output;
	}
	
	public boolean isRecord(){
		return true;
	}
	
	public static void main(String[] args) {
		Record rec = new Record();
		Field f1 = new Field();
		f1.setName("field 1 name");
		f1.setData("field 1 content");
		rec.addField(f1);
		rec.setName("Record");
		System.out.println(rec.getField("field 2 name"));
		System.out.println(rec.toString());
		rec.swap(1, 0);
		System.out.println(rec.toString());
		//rec.deleteField(f1);
		System.out.println(rec.toString());
		
		
	}
}

	
