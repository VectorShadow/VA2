package world.item.loadout;

public interface Equippable {
    enum EquipmentSlot {
        WIELDED,
    }
    EquipmentSlot getEquipmentSlot();
}
