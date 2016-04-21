package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MiscUtils {
	
	protected static final Color BUTTON_COLOUR_LIGHT = new Color(255, 205, 40);
	/*protected static final Color BUTTON_COLOUR_LIGHT = new Color(255, 205, 40);
	protected static final Color BUTTON_COLOUR_LIGHT = new Color(255, 205, 40);
	
	protected static final Color BUTTON_COLOUR_LIGHT = new Color(255, 205, 40);
	protected static final Color BUTTON_COLOUR_LIGHT = new Color(255, 205, 40);*/
	
	protected static BufferedImage getBufferedGradImage(Color c1, Color c2, int w, int h, boolean d){
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setBackground(Color.WHITE);
		GradientPaint gp;
		if(d)
			gp = new GradientPaint(0, 0, c1, 0, h, c2);
		else
			gp = new GradientPaint(0, h, c1, 0, 0, c2);
		g2d.setPaint(gp);
		g2d.fill(new Rectangle(0, 0, w, h));
		g2d.dispose();
		return bi;
	}
	
	
	
	protected static BufferedImage getBufferedImageFromFile(String fileName, int width){
		
		float scale = 1f;

		Image img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scale = (float)width / (float)img.getWidth(null);
		System.out.println(scale);
		System.out.println(scale * img.getWidth(null));
		System.out.println(scale * img.getHeight(null));
		BufferedImage bi = new BufferedImage((int)(scale * (float)img.getWidth(null)),
												(int)(scale * (float)img.getHeight(null)),
												BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.scale(scale, scale);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return bi;
	}
	
}
