package drinkware;

/* abstract factory — defines the contract for creating draggable game items */
public abstract class ItemFactory {
    public abstract DraggableItem createItem(String type, double x, double y);
}
