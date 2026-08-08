package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Mug {
	private double xPos, yPos;
	private boolean hasEspresso = false;
	private boolean hasLatte = false;
	public static final int R = 40;

	public Mug(double x, double y) {
		xPos = x;
		yPos = y;
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
		int cx = (int) xPos;
		int cy = (int) yPos;

		if (hasLatte) {
			g2.setColor(Color.WHITE);
			g2.fillOval(cx - R, cy - R, R * 2, R * 2);
		} else if (hasEspresso) {
			g2.setColor(Color.BLACK);
			g2.fillOval(cx - R, cy - R, R * 2, R * 2);
		}

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(cx - R, cy - R, R * 2, R * 2);
		g2.setStroke(new BasicStroke(1));
	}
}
