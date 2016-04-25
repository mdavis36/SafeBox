package gui;
import java.awt.Color;



import javax.swing.*;
public abstract class MessageBoxState extends JDialog{

	private static final long serialVersionUID = 1L;
	protected JLabel titleLabel;
	final static String cancel = "Cancel";
	protected static int BAR_HEIGHT = 60;
	protected int BAR_WIDTH = 400;
	protected CustomButton cancelButton = new CustomButton("Cancel", 0, 0, 80, (int)(BAR_HEIGHT * 0.6));
	protected JPanel panel = new JPanel();

}