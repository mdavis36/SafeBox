package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomDialog extends JDialog{

	protected JPanel northPanel = new JPanel();
	protected JPanel centerPanel = new JPanel();
	protected JPanel southPanel = new JPanel();
	
	
	public CustomDialog(Color c1, Color c2, int w, int h){
		BackgroundPanel contentPane = new BackgroundPanel(MiscUtils.getBufferedGradImage(c1, c2, w, h, true));
		contentPane.setLayout(new BorderLayout());
		contentPane.setTransparentAdd(true);
		
		northPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		southPanel.setOpaque(false);
		
		northPanel.add(new JLabel("Test"));
		
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		this.setSize(new Dimension(w, h));
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane);
	}
	
	
	
	public void open(){
		setVisible(true);
	}
	
	public void close(){
		
		setVisible(false);
	}
	
	
}
