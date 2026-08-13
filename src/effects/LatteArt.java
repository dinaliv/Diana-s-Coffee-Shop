package effects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/* draws recursive layered heart latte art on the cup */
public class LatteArt {

    public void drawHeart(Graphics2D g2, float x, float y, float s) {

        Path2D.Float heart = new Path2D.Float();

        heart.moveTo(0, -0.20f * s);
        heart.curveTo(0.28f * s, -0.55f * s, 0.75f * s, -0.20f * s, 0.75f * s,  0.20f * s);
        heart.curveTo(0.75f * s, 0.65f * s, 0.35f * s, 0.95f * s, 0, 1.20f * s);
        heart.curveTo(-0.35f * s, 0.95f * s, -0.75f * s, 0.65f * s, -0.75f * s, 0.20f * s);
        heart.curveTo(-0.75f * s, -0.20f * s, -0.28f * s, -0.55f * s, 0, -0.20f * s);

        heart.closePath();

        Stroke stroke = g2.getStroke();
        g2.setStroke(new BasicStroke(4f));
        g2.setColor(new Color(180, 120, 60));
        g2.draw(heart);
        g2.setStroke(stroke);

    }

    // layered hearts
    public void drawLatteArt(Graphics2D g2, float x, float y, float s) {
    	AffineTransform at = g2.getTransform();
        g2.translate(x, y);
        drawHeart(g2, x, y, s);
        g2.setTransform(at);
    	if (s > 30) {
    		s *= 0.7;
    		drawLatteArt(g2, x, y, s);
    	}

    }
}
