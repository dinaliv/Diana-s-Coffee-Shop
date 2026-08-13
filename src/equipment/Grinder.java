package equipment;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/* coffee grinder — grinds beans on a timer when portafilter is docked */
public class Grinder implements ActionListener {

	private double xPos, yPos;
	private boolean isGrinding = false;
	private boolean grindDone = false;
	private Timer grindTimer;
	private BufferedImage img;

	public Grinder(double x, double y) {
		xPos = x;
		yPos = y;
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/assets/Grinder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		return xPos - 5;
	}

	public double getDockY() {
		return yPos + 66;
	}

	public void draw(Graphics2D g2) {
		if (img != null) {
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(img, (int)(xPos - 170), (int)(yPos - 192), 340, 435, null);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}
	}
}
