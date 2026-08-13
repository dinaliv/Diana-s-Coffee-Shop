package equipment;

/* concrete factory — instantiates the correct equipment based on type string */
public class CoffeeEquipmentFactory extends EquipmentFactory {
    @Override
    public CoffeeEquipment createEquipment(String type, double x, double y) {
        if (type.equals("grinder"))         return new Grinder(x, y);
        if (type.equals("espressomachine")) return new EspressoMachine(x, y);
        return null;
    }
}
