package world.item;

import world.item.loadout.EquipmentSlot;
import world.item.material.Material;

/**
 * This class specifies all items which may be equipped by the player.
 * Such items generally either interact in combat, degrade for other reasons, such as by consuming fuel,
 * so this class extend Degradeable. If an equipped item need not degrade for any reason, set doesDegrade to false.
 */
public class EquipableItem extends DegradableItem {

    private final EquipmentSlot EQUIPMENT_SLOT;

    public EquipableItem(ItemTemplate it, boolean doesDegrade, Material m, EquipmentSlot es) {
        super(it, doesDegrade, m);
        EQUIPMENT_SLOT = es;
    }
    EquipableItem(EquipableItem ei) {
        this (((ItemTemplate)ei.getTemplate()), ei.DOES_DEGRADE, ei.getMaterial(), ei.getEquipmentSlot());
    }


    public EquipmentSlot getEquipmentSlot() {
        return EQUIPMENT_SLOT;
    }

    @Override
    public EquipableItem clone() {
        return new EquipableItem(this);
    }
}
