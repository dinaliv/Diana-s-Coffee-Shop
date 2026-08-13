package drinkware;

import equipment.Portafilter;

/* concrete factory — instantiates the correct draggable item based on type string */
public class CoffeeItemFactory extends ItemFactory {
    @Override
    public DraggableItem createItem(String type, double x, double y) {
        if (type.equals("portafilter")) return new Portafilter(x, y);
        if (type.equals("cup"))         return new Cup(x, y);
        if (type.equals("pitcher"))     return new Pitcher(x, y);
        return null;
    }
}
