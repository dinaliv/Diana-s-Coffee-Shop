package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Pitcher {
	private double xPos, yPos;
	private boolean dragging = false;
	private boolean steamed = false;

	private static final int W = 36;
	private static final int H = 56;

	public Pitcher(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public void draw(Graphics2D g2) {
		int cx = (int) xPos;
		int cy = (int) yPos;

		g2.setStroke(new BasicStroke(2));

		// cylinder body
		g2.setColor(Color.WHITE);
		g2.fillRect(cx - W / 2, cy - H / 2 + 7, W, H - 7);
		g2.setColor(Color.BLACK);
		g2.drawRect(cx - W / 2, cy - H / 2 + 7, W, H - 7);

		// top ellipse
		g2.setColor(Color.WHITE);
		g2.fillOval(cx - W / 2, cy - H / 2, W, 14);
		g2.setColor(Color.BLACK);
		g2.drawOval(cx - W / 2, cy - H / 2, W, 14);

		g2.setStroke(new BasicStroke(1));
	}

	public boolean clicked(double mx, double my) {
		return mx >= xPos - W / 2 && mx <= xPos + W / 2
			&& my >= yPos - H / 2 && my <= yPos + H / 2;
	}

	public boolean hitSteamWand(EspressoMachine machine) {
		return Math.abs(xPos - machine.getSteamWandX()) < 90
			&& Math.abs(yPos - (machine.getSteamWandY() + 25)) < 90;
	}

	public boolean hitTable(int counterY) {
		return yPos > counterY - 80;
	}

	public boolean hitMug(Mug mug) {
		double dx = xPos - mug.getXPos();
		double dy = yPos - mug.getYPos();
		return Math.sqrt(dx * dx + dy * dy) < 70;
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

	public boolean isSteamed() {
		return steamed;
	}

	public void setSteamed(boolean b) {
		steamed = b;
	}
}
