package equipment;

/* abstract factory — defines the contract for creating coffee equipment */
public abstract class EquipmentFactory {
    public abstract CoffeeEquipment createEquipment(String type, double x, double y);
}
