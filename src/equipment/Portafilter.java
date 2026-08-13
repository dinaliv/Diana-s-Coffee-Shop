package equipment;

import drinkware.DraggableItem;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portafilter extends DraggableItem {
    private double width = 60, height = 90;
    private boolean hasGrinds = false;
    private BufferedImage img;

    private static final int IMG_SIZE = 220;

    public Portafilter(double x, double y) {
        super(x, y);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/assets/Portafilter_cropped.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean clicked(double x, double y) {
        return x >= xPos - width / 2 && x <= xPos + width / 2
            && y >= yPos - height / 2 && y <= yPos + height / 2;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (img != null) {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(img, (int)(xPos - IMG_SIZE / 2), (int)(yPos - IMG_SIZE / 2), IMG_SIZE, IMG_SIZE, null);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    }

    public boolean hitGrinder(Grinder g) {
        return Math.abs(xPos - g.getDockX()) < 65
            && Math.abs(yPos - g.getDockY()) < 65;
    }

    public boolean hit(EspressoMachine machine) {
        return Math.abs(xPos - machine.getDockX()) < 65
            && Math.abs(yPos - machine.getDockY()) < 65;
    }

    public boolean hasGrinds() {
        return hasGrinds;
    }

    public void setHasGrinds(boolean b) {
        hasGrinds = b;
    }
}
