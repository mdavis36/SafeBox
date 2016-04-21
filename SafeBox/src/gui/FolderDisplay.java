package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class FolderDisplay extends BackgroundPanel{

	private Border border;
	private int boarderWidth = 3;
	
	private static final int DISPLAY_WIDTH = 200;
	private static final int DISPLAT_HEIGHT = 500;
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(new Color(218, 232, 252), new Color(126, 166, 224), DISPLAY_WIDTH, sm.window.getHeight(), true));
		setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAT_HEIGHT));
		setLayout(new BorderLayout(0, 5));
		setBounds(0, 0, DISPLAY_WIDTH, DISPLAT_HEIGHT);
		
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, new Color(108, 142, 191));
		setBorder(border);
		setOpaque(true);
		
		
		JButton btnNewButton_1 = new JButton("folder display");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setPreferredSize(new Dimension(199, 600));
		add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
	}
}
