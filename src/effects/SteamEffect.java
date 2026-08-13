package effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import processing.core.PApplet;

public class SteamEffect {
	private static final int W = 24;
	private static final int H = 37;

	private float xPos, yPos;
	private float xStart;
	private float xSeed;
	private float ySeed;
	private PApplet pa;

	public SteamEffect(float x, float y) {
		xPos = x;
		yPos = y;
		pa = new PApplet();
		xStart = pa.random(10);
		xSeed = xStart;
		ySeed = pa.random(10);
	}

	public void update() {
		ySeed += 0.05f;
	}

	public void draw(Graphics2D g2) {
		float noiseFactor;

		for (int y = 0; y <= H; y += 12) {
			ySeed += 0.05f;
			xSeed = xStart;

			for (int x = 0; x <= W; x += 12) {
				xSeed += 0.05f;
				noiseFactor = pa.noise(xSeed, ySeed);

				AffineTransform at = g2.getTransform();
				g2.translate(xPos + x, yPos + y);

				float offsetX = (pa.noise(xSeed + 5, ySeed) - 0.5f) * 26;
				float offsetY = (pa.noise(xSeed, ySeed + 5) - 0.5f) * 26;

				int diameter = (int)(noiseFactor * 55);
				int grey = (int)(150 + (noiseFactor * 105));
				int alph = (int)(150 + (noiseFactor * 105));
				g2.setColor(new Color(grey, grey, grey, alph));
				g2.fill(new Ellipse2D.Float(offsetX - diameter / 2f, offsetY - diameter / 2f, diameter, diameter));

				g2.setTransform(at);
			}
		}
	}
}
