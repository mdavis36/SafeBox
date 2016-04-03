package core;

public class HintManager {
	final static String HINT_NAME = "hint";
	
	public static void setHint(String hint) {
		PlainDataManager.setElement(HINT_NAME, hint);
	}
	
	public static String getHint() {
		return PlainDataManager.getElement(HINT_NAME);
	}
	
	public static void main(String[] args) {
		setHint("hi");
		
		System.out.println(getHint());
	}
}
