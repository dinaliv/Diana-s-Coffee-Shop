package drinkware;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cup extends DraggableItem {
    private boolean hasEspresso = false;
    private BufferedImage img;

    private static final int CUP_W = 54;
    private static final int CUP_H = 38;
    private static final int IMG_SIZE = 160;

    public Cup(double x, double y) {
        super(x, y);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/assets/espresso_shot_cup2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEspresso() {
        hasEspresso = true;
    }

    public void reset() {
        hasEspresso = false;
    }

    @Override
    public boolean clicked(double x, double y) {
        return x >= xPos - CUP_W / 2.0 && x <= xPos + CUP_W / 2.0
            && y >= yPos - CUP_H / 2.0 && y <= yPos + CUP_H / 2.0;
    }

    public boolean hitMug(Mug mug) {
        double dx = xPos - mug.getXPos();
        double dy = yPos - mug.getYPos();
        return Math.sqrt(dx * dx + dy * dy) < 65;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (img != null) {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(img, (int)(xPos - IMG_SIZE / 2), (int)(yPos - IMG_SIZE / 2), IMG_SIZE, IMG_SIZE, null);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
        if (hasEspresso) {
            g2.setColor(new Color(80, 40, 10));
            g2.fillRect((int)(xPos - 29), (int)(yPos + 12), 38, 27);
        }
    }
}
