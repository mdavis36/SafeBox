package core;

public class HintManager {
	
	public static void setHint(String hint) {
		PlainDataManager.setElement(Consts.HINT_NAME_PDM_ENTRY_NAME, hint);
		PlainDataManager.saveToFile();
	}
	
	public static String getHint() {
		return PlainDataManager.getElement(Consts.HINT_NAME_PDM_ENTRY_NAME);
	}
	
	public static void main(String[] args) {
		setHint(Consts.HINT_NAME_PDM_ENTRY_NAME);
		System.out.println(getHint());
	}
}
