package equipment;

import java.awt.Graphics2D;

/* abstract base for stationary coffee equipment — enforces a draw method */
public abstract class CoffeeEquipment {
    protected double xPos, yPos;

    public CoffeeEquipment(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public abstract void draw(Graphics2D g2);
}
