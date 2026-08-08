package coffeeshop;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Grinder implements ActionListener {

	private double xPos, yPos;
	private boolean isGrinding = false;
	private boolean grindDone = false;
	private Timer grindTimer;

	public Grinder(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public void startGrinding() {
		isGrinding = true;
		grindDone = false;
		grindTimer = new Timer(3000, this);
		grindTimer.setRepeats(false);
		grindTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		isGrinding = false;
		grindDone = true;
	}

	public boolean isGrinding() {
		return isGrinding;
	}

	public boolean isGrindDone() {
		return grindDone;
	}

	// where the portafilter snaps when placed under the hole
	public double getDockX() {
		return xPos;
	}

	public double getDockY() {
		return yPos + 56;
	}

	public void draw(Graphics2D g2) {
		int x = (int)(xPos - 60);
		int y = (int)(yPos - 100);

		// body
		g2.setColor(Color.WHITE);
		g2.fillRect(x, y, 120, 200);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(x, y, 120, 200);
		g2.setStroke(new BasicStroke(1));

		// label
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.drawString("Grinder", x + 22, y + 52);

		// hole at the bottom centre
		g2.setColor(Color.DARK_GRAY);
		g2.fillOval(x + 44, y + 148, 32, 16);
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1.5f));
		g2.drawOval(x + 44, y + 148, 32, 16);
		g2.setStroke(new BasicStroke(1));
	}
}
