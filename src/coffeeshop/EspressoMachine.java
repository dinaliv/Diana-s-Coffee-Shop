package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class EspressoMachine implements ActionListener {
	private double xPos, yPos;
	private boolean buttonsActive = false;
	private boolean isPulling = false;
	private boolean shotReady = false;
	private int shotCount = 0;
	private Timer pullTimer;
	private BufferedImage img;

	private static final int BTN_W = 40;
	private static final int BTN_H = 40;

	public EspressoMachine(double x, double y) {
		xPos = x;
		yPos = y;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/Espresso_Machine_ 2k.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		if (img != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(img, left - 90, top - 70, 380, 437, null);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}

		// dripping coffee overlay when pulling shot
		if (isPulling) {
			g2.setColor(new Color(80, 40, 10));
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(left + 98, top + 130, left + 98, top + 237);
			g2.drawLine(left + 117, top + 130, left + 117, top + 237);
			g2.setStroke(new BasicStroke(1));
		}
	}

	public int shotButtonClicked(double x, double y) {
		if (!buttonsActive || isPulling) return 0;
		int left = (int)(xPos - 100);
		int top = (int)(yPos - 115);
		int bx1 = left + 10,  by1 = top + 28;
		int bx2 = left + 66,  by2 = top + 28;
		int bx3 = left + 122, by3 = top + 23;

		if (x >= bx1 && x <= bx1 + BTN_W && y >= by1 && y <= by1 + BTN_H) return 1;
		if (x >= bx2 && x <= bx2 + BTN_W && y >= by2 && y <= by2 + BTN_H) return 2;
		if (x >= bx3 && x <= bx3 + BTN_W && y >= by3 && y <= by3 + BTN_H) return 3;
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
		return yPos + 40;
	}

	// tip of the steam wand
	public double getSteamWandX() {
		return xPos + 150;
	}

	public double getSteamWandY() {
		return yPos + 35;
	}

	public int getShotCount() {
		return shotCount;
	}

	public void setShotCount(int n) {
		shotCount = n;
	}
}
