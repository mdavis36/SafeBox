package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MiscUtils {
	
	protected static BufferedImage getBufferedGradImage(Color c1, Color c2, int w, int h, boolean d){
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
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
	
	
}
