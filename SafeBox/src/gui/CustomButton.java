package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton{

	private final static float FONT_MULTIPLIER = 0.5f;
	
	
	public CustomButton(String text, int x, int y, int width, int height){
		super(text);
		setLocation(x, y);
		setSize(width, height);
		
		setBorderPainted(false);
		setBorder(null);
		setContentAreaFilled(false);
		
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setForeground(Color.WHITE);
		setFont(new Font(Consts.FONT_STYLE, Font.PLAIN, (int)(getHeight() * FONT_MULTIPLIER)));
	}

	
	/**
	 * @param c color of border
	 * @param w dimension of the border
	 */
	public void setBoarderDetails(Color c, int w){
		setBorderPainted(true);
		setBorder(BorderFactory.createMatteBorder(w, w, w, w, c));
	}
	
	/**
	 * @param fileName name of the image file
	 * @param keepRatio whether the ratio needs to change
	 */
	public void setImageFromFile(String fileName, boolean keepRatio){
		BufferedImage i = MiscUtils.getBufferedImageFromFile(Consts.BUTTON_PATH + fileName, getWidth());
		setImageIcon(i, keepRatio);	
	}
		
	/**
	 * @param c1 starting color
	 * @param c2 second color
	 * @param down if the gradient goes up or down
	 */
	public void setGradientBackground(Color c1, Color c2, boolean down){
		BufferedImage i = MiscUtils.getBufferedGradImage(c1, c2, getWidth(), getHeight(), down);
		setImageIcon(i, true);
	}
	
	/**
	 * sets the image on the button
	 */
	public void setImageIcon(Image image, boolean keepRatio){
		int tmp_height = getHeight();
		if (keepRatio){
			float ratio = image.getWidth(getParent())/ image.getHeight(getParent()) ;
			tmp_height =(int) (getWidth() / ratio);
		}
		Image small = image.getScaledInstance(getWidth(), tmp_height, Image.SCALE_SMOOTH);
		setSize(getWidth(), tmp_height);
		setIcon(new ImageIcon(small));
		setSize(getWidth(),tmp_height);
	}
}














