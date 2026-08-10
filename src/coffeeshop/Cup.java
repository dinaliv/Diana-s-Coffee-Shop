package coffeeshop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cup {
	private double xPos, yPos;
	private boolean hasEspresso = false;
	private boolean dragging = false;
	private BufferedImage img;

	private static final int CUP_W = 54;
	private static final int CUP_H = 38;
	private static final int IMG_SIZE = 160;

	public Cup(double x, double y) {
		xPos = x;
		yPos = y;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/espresso_shot_cup.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addEspresso() {
		hasEspresso = true;
	}

	public void reset() {
		hasEspresso = false;
	}

	public boolean clicked(double x, double y) {
		return x >= xPos - CUP_W / 2.0 && x <= xPos + CUP_W / 2.0
			&& y >= yPos - CUP_H / 2.0 && y <= yPos + CUP_H / 2.0;
	}

	public boolean hitMug(Mug mug) {
		double dx = xPos - mug.getXPos();
		double dy = yPos - mug.getYPos();
		return Math.sqrt(dx * dx + dy * dy) < 65;
	}

	public void draw(Graphics2D g2) {
		if (img != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(img, (int)(xPos - IMG_SIZE / 2), (int)(yPos - IMG_SIZE / 2), IMG_SIZE, IMG_SIZE, null);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
		if (hasEspresso) {
			g2.setColor(new Color(80, 40, 10));
			g2.fillRect((int)(xPos - 29), (int)(yPos), 35, 37);
		}
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setXPos(double x) {
		xPos = x;
	}

	public void setYPos(double y) {
		yPos = y;
	}

	public boolean isDragging() {
		return dragging;
	}

	public void setDragging(boolean b) {
		dragging = b;
	}
}
