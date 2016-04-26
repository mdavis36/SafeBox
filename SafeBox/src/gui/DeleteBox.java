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

public class DeleteBox extends MessageBoxState {
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
	private static final int GRID_Y_2 = 1;
	private static final int EMPTY_BORDER_TOP = 10;
	private static final int DELETE_BOX_WIDTH = 450;
	private static final int DELETE_BOX_HEIGHT = 180;
	
	//Strings Start//
	static final String delete = "Delete";
	static final String title = "Delete:";
	static final String currentName = "Placeholder String";
	//Strings End//
	
	//JObjects Start//
	private static CustomButton deleteButton = new CustomButton(delete, 0, 0, BUTTON_WIDTH, (int) (BAR_HEIGHT * BUTTON_HEIGHT_RATIO));
	private JLabel nameOfFolder;
	//JObjects End//
	
	public DeleteBox(final StateManager sm){
		final StateManager state = sm;
		//Buttons Start//
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		deleteButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		deleteButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER, BUTTON_BORDER_WIDTH);
		cancelButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT, MiscUtils.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, BUTTON_BORDER_WIDTH);
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}
		});
		
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//TODO: Finish implementation
				//state.getESM().getFileSystemHandler().deleteFolder(state., index)
				resetBox();
			}
		});
		//Buttons End//
		
		//Title Start//
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = GRID_WIDTH;
		c.gridy = GRID_Y_1;
		nameOfFolder = new JLabel(currentName);
		titleLabel = new JLabel(title);
		titlePanel = new JPanel(new GridBagLayout());
		titleLabel.setBorder(new EmptyBorder(EMPTY_BORDER_TOP, 0, 0, 0));
		titleLabel.setFont(new Font(MiscUtils.FONT_STYLE, Font.BOLD, FONT_SIZE));
		titlePanel.add(titleLabel,c);
		c.gridy = GRID_Y_2;
		titlePanel.add(nameOfFolder,c);
		titlePanel.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		//Title End//
		
		// Setting Frame Start//
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(buttons, BorderLayout.SOUTH);
		setSize(new Dimension(DELETE_BOX_WIDTH, DELETE_BOX_HEIGHT));
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		// Setting Frame End//
	}

	@Override
	protected void resetBox() {
		this.setVisible(false);
	}
}
