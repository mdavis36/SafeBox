package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import core.Record;

public class FieldBox extends BackgroundPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4657486320326798845L;
	private int index;

	private Border border;
	private int boarderWidth = 2;

	private static final int TEXT_SIZE = 20;
	private static final int FIELD_DIALOGUE_WIDTH = 450;
	private static final int FIELD_DIALOGUE_HEIGHT = 220;
	private static final int EDIT_BUTTON_WIDTH_HEIGHT = 30;
	private static final int FIELD_BORDER_TOP_BOTTOM = 10;
	private static final int FIELD_BORDER_LEFT_RIGHT = 20;
	private final int TIMER = 500;//time to show copied to clipboard in ms
	private JLabel fieldName;
	private JLabel fieldData;
	private String placeholder;

	public FieldBox(int x, int y, int width, int height, final int index, final StateManager sm) {
		super(MiscUtils.getBufferedGradImage(Consts.ORANGE_PANEL_COLOUR_LIGHT, Consts.ORANGE_PANEL_COLOUR_DARK, width, height, true));
		this.index = index;
		final EditFieldDialog b = new EditFieldDialog(sm, Consts.BLUE_PANEL_COLOUR_LIGHT, Consts.BLUE_PANEL_COLOUR_DARK, FIELD_DIALOGUE_WIDTH, FIELD_DIALOGUE_HEIGHT, this.index);
		b.setVisible(false);
		setSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout(0, 0));
		this.setBorder(new EmptyBorder(FIELD_BORDER_TOP_BOTTOM, FIELD_BORDER_LEFT_RIGHT, FIELD_BORDER_TOP_BOTTOM, FIELD_BORDER_LEFT_RIGHT));

		JPanel dataPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//----------------------Border---------------------
		border = BorderFactory.createMatteBorder(boarderWidth, boarderWidth, boarderWidth, boarderWidth, Consts.ORANGE_PANEL_COLOUR_BORDER);
		setBorder(border);
		setOpaque(true);
		setTransparentAdd(true);

		fieldName = new JLabel(((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getName() + " :");
		fieldName.setFont(new Font(Consts.FONT_STYLE, Font.BOLD, TEXT_SIZE));
		fieldName.setForeground(Color.BLACK);
		fieldName.setBackground(Color.WHITE);
		fieldName.setOpaque(false);
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 1.0;
		c.gridy = 0;
		dataPanel.add(fieldName, c);

		fieldData = new JLabel("    " + ((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getData());
		fieldData.setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, TEXT_SIZE));
		fieldData.setForeground(Color.BLACK);
		fieldData.setBackground(Color.WHITE);
		fieldData.setOpaque(false);
		fieldData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				placeholder = fieldData.getText();
				fieldData.setText(English.CLIPBOARD_NOTICE);
				MiscUtils.setClipboard(((Record) sm.getESM().getFileSystemHandler().getCurrentRecord().getData()).getField(index).getData());
				clipboardNotice();

			}
		});
		c.gridy = 1;
		dataPanel.add(fieldData, c);

		CustomButton editButton = new CustomButton("", 0, 0, EDIT_BUTTON_WIDTH_HEIGHT, EDIT_BUTTON_WIDTH_HEIGHT);
		editButton.setImageFromFile(Consts.PEN_IMAGE, true);
		editButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				b.open();
			}
		});

		add(editButton, BorderLayout.EAST);
		add(dataPanel, BorderLayout.WEST);

	}

	private void clipboardNotice() {
		Timer t = new Timer(TIMER, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fieldData.setText(placeholder);
			}
		});
		t.setRepeats(false);
		t.start();
	}
}
