package drinkware;

import java.awt.Color;
import java.awt.Graphics2D;

/* decorator that adds an espresso crema layer inside the mug when espresso is poured */
public class EspressoLayerDecorator extends MugDecorator {

    public EspressoLayerDecorator(MugDrawable wrapped, Mug mug) {
        super(wrapped, mug);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        int x = (int) mug.getXPos();
        int y = (int) mug.getYPos();
        // outer crema ring
        g2.setColor(new Color(130, 75, 20));
        g2.fillOval(x - 22, y - 62, 58, 58);
        // dark espresso centre
        g2.setColor(new Color(55, 22, 5));
        g2.fillOval(x - 13, y - 53, 40, 40);
    }
}
