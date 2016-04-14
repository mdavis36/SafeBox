package gui;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class testGUI {
	private static final String TITLE = "SafeBox";
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	static JPanel cards;
	static CardLayout cl;
	
	public static void main(String[] args){		
		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(WIDTH, HEIGHT);
		window.setVisible(true);

		//Initialize seperate Panels
		JPanel passCard = new JPanel();
		JPanel mainCard = new JPanel();
		
		//Initialize cards
		
		cards = new JPanel(new CardLayout());
		cl = (CardLayout) cards.getLayout();
		
		
		//---------------------------------------------------
		
		cards.add(passCard, "password");
		passCard.setLayout(null);
		
		
		final JButton btnNewButton = new JButton("Enter SafeBox");
		btnNewButton.setBounds(321, 243, 136, 50);
		passCard.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e){
				btnNewButton.setText("mouse in");
			}
			public void mouseExited(MouseEvent e){
				btnNewButton.setText("Go To main");
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cards, "main");
			}
		});
		
		JLabel lblPasscard = new JLabel("SafeBox");
		lblPasscard.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasscard.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblPasscard.setBounds(321, 75, 100, 50);
		passCard.add(lblPasscard);
		
		JButton btnNewButton_2 = new JButton("Forgot Password");
		btnNewButton_2.setBounds(321, 326, 136, 50);
		passCard.add(btnNewButton_2);
		
		JLabel lblSafeboxLogo = new JLabel("// SAFEBOX LOGO GOES HERE");
		lblSafeboxLogo.setBounds(321, 170, 162, 14);
		passCard.add(lblSafeboxLogo);
		
		
		//------------------------------------------------
		
		cards.add(mainCard, "main");
		mainCard.setLayout(null);
		
		JLabel lblMaincard = new JLabel("MainCard");
		lblMaincard.setBounds(325, 9, 96, 14);
		mainCard.add(lblMaincard);
		
		JButton btnNewButton_1 = new JButton("Go to pass");
		btnNewButton_1.setBounds(338, 106, 83, 23);
		mainCard.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "password");
			}
		});
		
		//-------------------------------------------------
		window.getContentPane().add(cards);
		
		
		
		
		
		
		cl.show(cards, "main");
		
		
	}
}
