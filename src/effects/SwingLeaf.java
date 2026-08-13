package effects;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/* decorative leaf pinned at the upper-right corner that gently swings */
public class SwingLeaf {
    private BufferedImage img;
    private float time = 0;

    private static final int SIZE = 160;
    private static final float AMPLITUDE = 0.10f;
    private static final float SPEED = 0.03f;

    public SwingLeaf() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/assets/leaf.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        time += SPEED;
    }

    public void draw(Graphics2D g2, int panelWidth) {
        if (img == null) return;

        float angle = AMPLITUDE * (float) Math.sin(time);

        // pivot at the top-right corner where the stem tip sits
        int pivotX = panelWidth - 15;
        int pivotY = -5;

        AffineTransform saved = g2.getTransform();
        g2.rotate(angle, pivotX, pivotY);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, pivotX - SIZE, pivotY, SIZE, SIZE, null);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setTransform(saved);
    }
}
