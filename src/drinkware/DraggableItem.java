package drinkware;

import java.awt.Graphics2D;

/* abstract base for all draggable objects — holds position and drag state */
public abstract class DraggableItem {
    protected double xPos, yPos;
    private boolean dragging;

    public DraggableItem(double x, double y) {
        xPos = x;
        yPos = y;
    }

    public abstract void draw(Graphics2D g2);
    public abstract boolean clicked(double x, double y);

    public double getXPos() { 
    	return xPos; 
    }
    public double getYPos() { 
    	return yPos; 
    }
    public void setXPos(double x) { 
    	xPos = x; 
    }
    public void setYPos(double y) { 
    	yPos = y; 
    }
    public boolean isDragging() { 
    	return dragging; 
    }
    public void setDragging(boolean b) { 
    	dragging = b; 
    }
}
