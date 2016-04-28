package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CustomDialog extends JDialog{

	protected JPanel northPanel = new JPanel();
	protected JPanel centerPanel = new JPanel();
	protected JPanel southPanel = new JPanel();
	protected BackgroundPanel contentPane;
	protected Point location;
	protected static final int BORDER_WIDTH = 15;
	
	protected StateManager sm;
	
	public CustomDialog(final StateManager sm, Color c1, Color c2, int w, int h){
		this.sm = sm;
		
		contentPane = new BackgroundPanel(MiscUtils.getBufferedGradImage(c1, c2, w, h, true));
		contentPane.setLayout(new BorderLayout(10,10));
		contentPane.setTransparentAdd(true);
		contentPane.setBorder(new EmptyBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH));
		
		northPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		southPanel.setOpaque(false);
		
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		JDialog.setDefaultLookAndFeelDecorated(false);
		this.setUndecorated(true);
		this.setSize(new Dimension(w, h));
		this.setResizable(false);
		this.setModal(true);
		this.setContentPane(contentPane);
	}
	
	
	
	public void open(){
		this.setLocationRelativeTo(sm.getWindow());
		setVisible(true);
	}
	
	public void close(){
		sm.update();
		setVisible(false);
		
	}
	
	protected CustomButton setupButton(String text, int w, int h){
		CustomButton b = new CustomButton(text, 0, 0, w, h);
		b.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		b.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, 2);
		return b;
	}
	
	
}
