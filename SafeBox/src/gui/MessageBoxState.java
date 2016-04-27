package gui;
import java.awt.Color;




import javax.swing.*;
public abstract class MessageBoxState extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8244504120202183073L;
	protected String titleText;
	protected JLabel titleLabel = new JLabel();
	protected JPanel titlePanel = new JPanel();
	final static String cancel = "Cancel";
	protected static int BAR_HEIGHT = 60;
	protected static final int LONG_BUTTON_WIDTH = 150;
	protected static final int SHORT_BUTTON_WIDTH = 80;
	protected static final int BUTTON_HEIGHT = 36;
	protected int BAR_WIDTH = 400;
	protected CustomButton cancelButton = new CustomButton(cancel, 0, 0, SHORT_BUTTON_WIDTH, BUTTON_HEIGHT);
	protected JPanel title = new JPanel();
	protected JPanel panel = new JPanel();
	protected JPanel buttons = new JPanel();
	protected static StateManager sm;
	
	protected void drawButton(CustomButton b){
		b.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT,
				Consts.BUTTON_COLOUR_DARK, true);
		b.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, 2);
	}
	protected abstract void resetBox();
}