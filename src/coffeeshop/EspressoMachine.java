package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class EspressoMachine implements ActionListener {
	private double xPos, yPos;
	private boolean buttonsActive = false;
	private boolean isPulling = false;
	private boolean shotReady = false;
	private int shotCount = 0;
	private Timer pullTimer;

	private static final int BTN_W = 40;
	private static final int BTN_H = 30;

	public EspressoMachine(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public void startPulling() {
		isPulling = true;
		shotReady = false;
		pullTimer = new Timer(5000, this);
		pullTimer.setRepeats(false);
		pullTimer.start();
	}

	public void actionPerformed(ActionEvent e) {
		isPulling = false;
		shotReady = true;
	}

	public void draw(Graphics2D g2) {
		int left = (int)(xPos - 100);
		int top = (int)(yPos - 115);

		//machine body
		g2.setColor(Color.WHITE);
		g2.fillRect(left, top, 200, 230);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(left, top, 200, 230);
		g2.setStroke(new BasicStroke(1));

		//shot buttons
		String[] labels = {"1", "2", "3"};
		for (int i = 0; i < 3; i++) {
			int bx = left + 18 + i * 56;
			int by = top + 35;
			g2.setColor(Color.WHITE);
			g2.fillRect(bx, by, BTN_W, BTN_H);
			g2.setColor(Color.BLACK);
			g2.drawRect(bx, by, BTN_W, BTN_H);
			g2.setFont(new Font("Arial", Font.BOLD, 14));
			g2.drawString(labels[i], bx + 13, by + 22);
		}

		//portafilter basket dock 
		int[] dockX = { left + 50, left + 150, left + 140, left + 60 };
		int[] dockY = { top + 80, top + 80, top + 130, top + 130 };
		g2.setColor(Color.WHITE);
		g2.fillPolygon(dockX, dockY, 4);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(dockX, dockY, 4);

		//horizontal line inside dock
		g2.drawLine(left + 55, top + 105, left + 145, top + 105);

		//dripping coffee
		if (isPulling) {
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(left + 83, top + 130, left + 83, top + 200);
			g2.drawLine(left + 117, top + 130, left + 117, top + 200);
			g2.setStroke(new BasicStroke(1));
		}

		//drip tray at the bottom
		g2.setColor(Color.WHITE);
		g2.fillRect(left + 30, top + 205, 140, 18);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(left + 30, top + 205, 140, 18);
		g2.setStroke(new BasicStroke(1));

		//steam wand
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(left + 200, top + 60, left + 230, top + 60);
		g2.drawLine(left + 230, top + 60, left + 230, top + 110);
		g2.setStroke(new BasicStroke(1));
	}

	public int shotButtonClicked(double x, double y) {
		if (!buttonsActive || isPulling) return 0;
		int left = (int)(xPos - 100);
		int top = (int)(yPos - 115);
		for (int i = 0; i < 3; i++) {
			int bx = left + 18 + i * 56;
			int by = top + 35;
			if (x >= bx && x <= bx + BTN_W && y >= by && y <= by + BTN_H)
				return i + 1;
		}
		return 0;
	}

	public void setButtonsActive(boolean active) {
		buttonsActive = active;
	}

	public boolean isShotReady() {
		return shotReady;
	}

	public boolean isPulling() {
		return isPulling;
	}

	public void resetShot() {
		shotReady = false;
	}

	public double getDockX() {
		return xPos;
	}

	public double getDockY() {
		return yPos - 10;
	}

	// tip of the steam wand 
	public double getSteamWandX() {
		return xPos + 130;
	}

	public double getSteamWandY() {
		return yPos - 5;
	}

	public int getShotCount() {
		return shotCount;
	}

	public void setShotCount(int n) {
		shotCount = n;
	}
}
