package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.glass.events.MouseEvent;

public class FolderDisplay extends BackgroundPanel{

	private Border border;
	private int boarderWidth = 3;
	
	private static final int DISPLAY_WIDTH = 250;
	private static final int DISPLAT_HEIGHT = 500;
	
	private JPanel toolBar = new JPanel(new BorderLayout(5,0));
	private JPanel centerBox = new JPanel(new FlowLayout(0));
	private JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	protected FolderDisplay(final StateManager sm){
		super(MiscUtils.getBufferedGradImage(new Color(218, 232, 252), new Color(126, 166, 224), DISPLAY_WIDTH, sm.window.getHeight(), true));
		setPreferredSize(new Dimension(DISPLAY_WIDTH, DISPLAT_HEIGHT));
		setLayout(new BorderLayout(0, 5));
		setBounds(0, 0, DISPLAY_WIDTH, DISPLAT_HEIGHT);
		
		
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, new Color(108, 142, 191));
		setBorder(border);
		setOpaque(true);
		
		//-------------------TOOLBAR--------------------
		CustomButton backButton = new CustomButton("", 0, 0, 50, 50);
		backButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		backButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sm.cl.show(sm.cards, sm.PASSWORD_STATE);
			}
		});
		
		JLabel directoryTitle = new JLabel(sm.getEFSM().getFileSystemHandler().getCurrent().getData().getName());
		directoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
		directoryTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		
		
		
		
		CustomButton homeButton = new CustomButton("", 0, 0, 50, 50);
		homeButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		homeButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		
		toolBar.add(backButton, BorderLayout.WEST);
		toolBar.add(directoryTitle, BorderLayout.CENTER);
		toolBar.add(homeButton, BorderLayout.EAST);
		
		//-------------------CENTERBOX---------------------
		
		//-------------------BOTTOMBAR---------------------
		CustomButton addRecordOrField = new CustomButton("", 0, 0, 15, 15);
		
		
		
		setTransparentAdd(true);
		add(toolBar, BorderLayout.NORTH);
	}
}
