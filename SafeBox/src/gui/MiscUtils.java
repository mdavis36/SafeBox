package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MiscUtils {
	
	private static final float ALPHA = 0.0f;
	
	/**
	 * @param c1 first color
	 * @param c2 second color
	 * @param w width of the image
	 * @param h height of the image
	 * @param d if the gradient goes up of down
	 * @return
	 */
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

	
	/**
	 * @param fileName name of the image file
	 * @param width of the image desired
	 * @return the image buffered to the width
	 */
	protected static BufferedImage getBufferedImageFromFile(String fileName, int width){
		
		float scale = 1f;

		Image img = null;
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		scale = (float)width / (float)img.getWidth(null);
		BufferedImage bi = new BufferedImage((int)(scale * (float)img.getWidth(null)),
												(int)(scale * (float)img.getHeight(null)),
												BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.scale(scale, scale);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		return bi;
	}
	
	protected static BufferedImage layerBufferedImages(BufferedImage bottom, BufferedImage top){
		BufferedImage bi = new BufferedImage(bottom.getWidth(), bottom.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.drawImage(bottom, 0, 0, null);
		g2d.drawImage(top, 0, 0, null);
		g2d.dispose();
		return bi;
	}
	
	
	public static void setClipboard(String value){
		StringSelection stringSelection = new StringSelection(value);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}
	
	/**
	 * @param val the input value
	 * @param min the minimum value to return
	 * @param max the maximum value to return
	 * @return returns val if min<val<max, min if val<min, and max if val>max
	 */
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	/**
	 * @param window the window to change the icon in
	 * @param path the path to the new icon
	 */
	public static void setIcon(JFrame window, String path){
		ImageIcon img = new ImageIcon(path);
		window.setIconImage(img.getImage());
	}
}
