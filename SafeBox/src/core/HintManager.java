package core;

public class HintManager {

	/**
	 * @param hint the text that is the hint for the password
	 */
	public static void setHint(String hint) {
		PlainDataManager.setElement(Consts.HINT_NAME_PDM_ENTRY_NAME, hint);
		PlainDataManager.saveToFile();
	}

	/**
	 * @return the string of the hint
	 */
	public static String getHint() {
		return PlainDataManager.getElement(Consts.HINT_NAME_PDM_ENTRY_NAME);
	}

	public static void main(String[] args) {
		setHint(Consts.HINT_NAME_PDM_ENTRY_NAME);
		System.out.println(getHint());
	}
}
