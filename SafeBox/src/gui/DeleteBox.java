package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	//Strings Start//
	static final String delete = "Delete";
	static final String title = "Delete:";
	static final String currentName = "Placeholder String";
	//Strings End//
	
	//JObjects Start//
	private static CustomButton deleteButton = new CustomButton(delete, 0, 0, 80, (int) (BAR_HEIGHT * 0.6));
	private JLabel nameOfFolder;
	//JObjects End//
	
	public DeleteBox(final StateManager sm){
		final StateManager state = sm;
		//Buttons Start//
		buttons.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		buttons.setLayout(new FlowLayout());
		deleteButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		deleteButton.setBoarderDetails(MiscUtils.ORANGE_PANEL_COLOUR_BORDER,
				2);
		cancelButton.setGradientBackground(MiscUtils.BUTTON_COLOUR_LIGHT,
				MiscUtils.BUTTON_COLOUR_DARK, true);
		cancelButton.setBoarderDetails(MiscUtils.BUTTON_COLOUR_BORDER, 2);
		cancelButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				resetBox();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});
		deleteButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: Finish implementation
				//state.getESM().getFileSystemHandler().deleteFolder(state., index)
				resetBox();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

		});
		//Buttons End//
		
		//Title Start//
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 4;
		c.gridy = 0;
		nameOfFolder = new JLabel(currentName);
		titleLabel = new JLabel(title);
		titlePanel = new JPanel(new GridBagLayout());
		titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		titlePanel.add(titleLabel,c);
		c.gridy = 1;
		titlePanel.add(nameOfFolder,c);
		titlePanel.setBackground(MiscUtils.BLUE_PANEL_COLOUR_DARK);
		//Title End//
		
		// Setting Frame Start//
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(titlePanel, BorderLayout.NORTH);
		panel.add(buttons, BorderLayout.SOUTH);
		setSize(new Dimension(450, 180));
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
