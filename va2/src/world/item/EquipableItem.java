package world.item;

import world.item.loadout.EquipmentSlot;

/**
 * This class specifies all items which may be equipped by the player.
 * Such items generally either interact in combat, degrade for other reasons, such as by consuming fuel,
 * so this class extend Degradeable. If an equipped item need not degrade for any reason, set doesDegrade to false.
 */
public abstract class EquipableItem extends DegradableItem {

    private final EquipmentSlot EQUIPMENT_SLOT;

    public EquipableItem(ItemTemplate it, boolean doesDegrade, EquipmentSlot es) {
        super(it, doesDegrade);
        EQUIPMENT_SLOT = es;
    }
    EquipableItem(EquipableItem ei) {
        this (((ItemTemplate)ei.getTemplate()), ei.DOES_DEGRADE, ei.getEquipmentSlot());
    }


    public EquipmentSlot getEquipmentSlot() {
        return EQUIPMENT_SLOT;
    }
}
