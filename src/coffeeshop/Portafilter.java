package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Portafilter {
	protected double xPos, yPos;
	private double width = 60, height = 90;
	private boolean dragging = false;
	private boolean hasGrinds = false;

	public Portafilter(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public boolean clicked(double x, double y) {
		return x >= xPos - width / 2 && x <= xPos + width / 2
			&& y >= yPos - height / 2 && y <= yPos + height / 2;
	}

	public void draw(Graphics2D g2) {
		g2.setStroke(new BasicStroke(2));

		// basket
		g2.setColor(Color.WHITE);
		g2.fillRoundRect((int)(xPos - 22), (int)(yPos - 15), 44, 28, 8, 8);
		g2.setColor(Color.BLACK);
		g2.drawRoundRect((int)(xPos - 22), (int)(yPos - 15), 44, 28, 8, 8);

		// triangular pile of coffee grinds 
		if (hasGrinds) {
			g2.setColor(new Color(55, 30, 10));
			int[] xs = { (int)xPos - 18, (int)xPos + 18, (int)xPos };
			int[] ys = { (int)yPos + 10, (int)yPos + 10, (int)yPos - 13 };
			g2.fillPolygon(xs, ys, 3);
		}

		// handle stem
		g2.setColor(Color.WHITE);
		g2.fillRoundRect((int)(xPos - 6), (int)(yPos + 13), 12, 38, 6, 6);
		g2.setColor(Color.BLACK);
		g2.drawRoundRect((int)(xPos - 6), (int)(yPos + 13), 12, 38, 6, 6);

		g2.setStroke(new BasicStroke(1));
	}

	public boolean hitGrinder(Grinder g) {
		return Math.abs(xPos - g.getDockX()) < 65
			&& Math.abs(yPos - g.getDockY()) < 65;
	}

	public boolean hit(EspressoMachine machine) {
		return Math.abs(xPos - machine.getDockX()) < 65
			&& Math.abs(yPos - machine.getDockY()) < 65;
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

	public boolean hasGrinds() {
		return hasGrinds;
	}

	public void setHasGrinds(boolean b) {
		hasGrinds = b;
	}
}
