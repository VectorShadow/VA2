package world.item;

import world.item.material.Material;

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
