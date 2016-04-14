package gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton{

	public CustomButton(String filePath, int x, int y, int width){
		super();
		Image i = null;
		try {
			i = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		float ratio = i.getWidth(getParent())/ i.getHeight(getParent()) ;
		int height =(int) (width / ratio);
		System.out.println(ratio + "   " + height + "  " + i.getHeight(getParent()) + "   " + i.getWidth(getParent()));
		Image small = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		i.toString();
		this.setIcon(new ImageIcon(small));
		this.setBounds(x, y, width, height);
	}
	
}
