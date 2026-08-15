package drinkware;

import java.awt.Graphics2D;

/* draws the plain mug image with no drink layers */
public class BaseMugLayer implements MugDrawable {
    private Mug mug;

    public BaseMugLayer(Mug mug) {
        this.mug = mug;
    }

    @Override
    public void draw(Graphics2D g2) {
        mug.draw(g2);
    }
}
