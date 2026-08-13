package drinkware;

import equipment.EspressoMachine;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/* milk pitcher — draggable, used for steaming milk and pouring into mug */
public class Pitcher extends DraggableItem {
    private boolean steamed = false;
    private BufferedImage img;

    private static final int W = 36;
    private static final int H = 56;
    private static final int IMG_SIZE = 161;

    public Pitcher(double x, double y) {
        super(x, y);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/assets/pitcher_cropped.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (img != null) {
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(img, (int)(xPos - IMG_SIZE / 2), (int)(yPos - IMG_SIZE / 2), IMG_SIZE, IMG_SIZE, null);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        }
    }

    @Override
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

    public boolean isSteamed() {
        return steamed;
    }

    public void setSteamed(boolean b) {
        steamed = b;
    }
}
