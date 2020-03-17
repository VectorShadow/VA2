package world.item;

import world.item.material.Material;

/**
 * This class specifies all contact interactive items which are considered to be innate to the combatant using them.
 * This type of item cannot itself be damaged, but instead causes the combatant to be damaged when a contact
 * interaction occurs.
 */
public class InnateItem extends InteractiveItem {
    public InnateItem(ItemTemplate it, Material m) {
        super(it, m);
    }

    @Override
    public double getSelfDamageMultiplier() {
        return 0.125; //do significantly less damage to our combatant than to a durable/degradeable item
    }

    @Override
    public boolean doesDamageSelf() {
        return false; //override default - instead damage the combatant
    }
}
