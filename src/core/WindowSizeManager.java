package core;

public class WindowSizeManager {

	/**
	 * @param width window width
	 * @param height window height
	 */
	public static void setSize(int width, int height) {
		PlainDataManager.setElement(Consts.PDM_WIDTH_NAME, Integer.toString(width));
		PlainDataManager.setElement(Consts.PDM_HEIGHT_NAME, Integer.toString(height));
		PlainDataManager.saveToFile();
	}

	/**
	 * @return width if stored, else -1
	 */
	public static int getWidth() {
		try {
			return Integer.parseInt(PlainDataManager.getElement(Consts.PDM_WIDTH_NAME));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * @return height if stored, else -1
	 */
	public static int getHeight() {
		try {
			return Integer.parseInt(PlainDataManager.getElement(Consts.PDM_HEIGHT_NAME));
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
