package coffeeshop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mug {
	private double xPos, yPos;
	private boolean hasEspresso = false;
	private boolean hasLatte = false;
	private BufferedImage img;

	public static final int R = 40;
	private static final int IMG_SIZE = 240;

	public Mug(double x, double y) {
		xPos = x;
		yPos = y;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/latte-mug.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addEspresso() {
		hasEspresso = true;
	}

	public void addLatte() {
		hasLatte = true;
	}

	public boolean hasLatte() {
		return hasLatte;
	}

	public double getXPos() {
		return xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void draw(Graphics2D g2) {
		if (img != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(img, (int)(xPos - IMG_SIZE / 2), (int)(yPos - IMG_SIZE / 2), IMG_SIZE, IMG_SIZE, null);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
		if (hasEspresso) {
			g2.setColor(new Color(80, 40, 10));
			g2.fillOval((int)(xPos), (int)(yPos - 60), 40, 40);
		}
	}
}
