package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Cup {
	private double xPos, yPos;
	private boolean hasEspresso = false;
	private boolean dragging = false;

	private static final int CUP_W = 54;
	private static final int CUP_H = 38;

	public Cup(double x, double y) {
		xPos = x;
		yPos = y;
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
		int x = (int)(xPos - CUP_W / 2);
		int y = (int)(yPos - CUP_H / 2);

		// half full when espresso has been pulled
		if (hasEspresso) {
			g2.setColor(Color.BLACK);
			g2.fillRect(x + 2, y + CUP_H / 2, CUP_W - 4, CUP_H / 2 - 2);
		}

		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(x, y, CUP_W, CUP_H);
		g2.setStroke(new BasicStroke(1));
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
