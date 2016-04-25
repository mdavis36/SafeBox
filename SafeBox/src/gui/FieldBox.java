package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import core.Field;

public class FieldBox extends BackgroundPanel{
	
private int index;
private int BAR_HEIGHT = 60;
	
	public FieldBox(Field field, int x, int y, int width, int height, int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(MiscUtils.BLUE_PANEL_COLOUR_LIGHT, MiscUtils.BLUE_PANEL_COLOUR_DARK, width, height, true));
		//super();
		this.index = index;
		setSize(new Dimension(width, height));
		setLayout(new FlowLayout(10));
		
		final JTextField fieldName = new JTextField(field.getName());
		fieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldName.setForeground(Color.BLACK);
		fieldName.setBackground(Color.WHITE);
		fieldName.setPreferredSize(new Dimension(100, (int)(BAR_HEIGHT * 0.6)));
		fieldName.setOpaque(true);
		
		final JTextField fieldData = new JTextField(field.getData());
		fieldData.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldData.setForeground(Color.BLACK);
		fieldData.setBackground(Color.WHITE);
		fieldData.setPreferredSize(new Dimension(100, (int)(BAR_HEIGHT * 0.6)));
		fieldData.setOpaque(true);
		
		CustomButton saveButton = new CustomButton("Save", 0, 0, 80, (int)(BAR_HEIGHT * 0.6));
		saveButton.setGradientBackground(new Color(255, 205, 40), new Color(255, 165, 0), true);
		saveButton.setBoarderDetails(new Color(215, 155, 0), 2);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(sm.window, "Saved", null, JOptionPane.PLAIN_MESSAGE);
				field.setName(fieldName.getText());
				field.setData(fieldData.getText());
			}
		});
		
	}
}
