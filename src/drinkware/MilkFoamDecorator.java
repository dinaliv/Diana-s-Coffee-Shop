package drinkware;

import java.awt.Color;
import java.awt.Graphics2D;
import effects.LatteArt;

/* decorator that adds steamed milk foam and latte art on top of the espresso layer */
public class MilkFoamDecorator extends MugDecorator {
    private LatteArt latteArt = new LatteArt();

    public MilkFoamDecorator(MugDrawable wrapped, Mug mug) {
        super(wrapped, mug);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        int x = (int) mug.getXPos();
        int y = (int) mug.getYPos();
        // cream-coloured foam oval covering the espresso layer
        g2.setColor(new Color(242, 224, 196));
        g2.fillOval(x - 24, y - 64, 62, 62);
        // latte art drawn on the foam
        latteArt.drawLatteArt(g2, x + 7, y - 33, 23);
    }
}
