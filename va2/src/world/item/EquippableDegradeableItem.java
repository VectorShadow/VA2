package world.item;

import world.item.loadout.Equippable;
import world.item.material.Material;

public class EquippableDegradeableItem extends DegradeableItem implements Equippable {

    private final EquipmentSlot EQUIPMENT_SLOT;

    public EquippableDegradeableItem(ItemTemplate it, Material m, EquipmentSlot es) {
        super(it, m);
        EQUIPMENT_SLOT = es;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EQUIPMENT_SLOT;
    }
}
