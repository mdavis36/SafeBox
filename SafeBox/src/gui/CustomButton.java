package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton{

	private final static String path = "res/buttons/";
	protected int c_width;
	protected int c_height;
	
	public CustomButton(String text, String fileName, int x, int y, int width, int height, boolean keepImgRatio){
		super(text);
		c_width = width;
		c_height = height;
		
		Image i = null;
		try {
			i = ImageIO.read(new File(path + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (keepImgRatio){
			float ratio = i.getWidth(getParent())/ i.getHeight(getParent()) ;
			c_height =(int) (c_width / ratio);
		}
		
		Image small = i.getScaledInstance(c_width, c_height, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(small));
		
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setForeground(Color.WHITE);
		setFont(new Font("Arial", Font.PLAIN, (int)(c_height * 0.6)));
		setBounds(x, y, c_width, c_height);	
	}
}
