package drinkware;

import java.awt.Graphics2D;

/* abstract decorator — wraps a MugDrawable and holds a Mug reference for position */
public abstract class MugDecorator implements MugDrawable {
    protected MugDrawable wrapped;
    protected Mug mug;

    public MugDecorator(MugDrawable wrapped, Mug mug) {
        this.wrapped = wrapped;
        this.mug = mug;
    }

    @Override
    public void draw(Graphics2D g2) {
        wrapped.draw(g2);
    }
}
