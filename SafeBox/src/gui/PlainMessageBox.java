package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PlainMessageBox extends MessageBoxState {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795604581523451482L;
	private static final int BUTTON_WIDTH = 80;
	private static final double BUTTON_HEIGHT_RATIO = .6;
	private static final int BUTTON_BORDER_WIDTH = 2;
	private static final int FONT_SIZE = 24;
	private static final int GRID_WIDTH = 4;
	private static final int GRID_Y_1 = 0;
	private static final int EMPTY_BORDER_TOP = 10;
	private static final int DELETE_BOX_WIDTH = 450;
	private static final int DELETE_BOX_HEIGHT = 180;
	
	//Strings Start//
	static final String delete = "Delete";
	static final String title = "Delete:";
	static final String currentName = "Placeholder String";
	//Strings End//
	
	//JObjects Start//
	private JLabel nameOfFolder;
	//JObjects End//
	
	public PlainMessageBox(final StateManager sm, String message){
		final StateManager state = sm;
		
		setSize(new Dimension(DELETE_BOX_WIDTH, DELETE_BOX_HEIGHT));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//Buttons Start//
		buttons.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		
		cancelButton.setText("OK");
		cancelButton.setGradientBackground(Consts.BUTTON_COLOUR_LIGHT, Consts.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(Consts.BUTTON_COLOUR_BORDER, BUTTON_BORDER_WIDTH);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}
		});
		buttons.add(cancelButton);
		//Buttons End//
		
		//Title Start//
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = GRID_WIDTH;
		c.gridy = GRID_Y_1;
		
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Consts.BLUE_PANEL_COLOUR_DARK);
		
		titleLabel = new JLabel(message);
		titleLabel.setBorder(new EmptyBorder(EMPTY_BORDER_TOP, 0, 0, 0));
		titleLabel.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, FONT_SIZE));
		titlePanel.add(titleLabel,c);
		//Title End//
		
		// Setting Frame Start//
		panel.setLayout(new BorderLayout());
		panel.add(titlePanel, BorderLayout.CENTER);
		panel.add(buttons, BorderLayout.SOUTH);
		
		add(panel);
		// Setting Frame End//
	}
	
	protected void setText(String text){
		titleLabel.setText(text);
	}

	@Override
	protected void resetBox() {
		this.setVisible(false);
	}
}
