package world.item;

import combat.ContactInteractive;
import world.item.material.Material;

public class InteractiveItem extends Item implements ContactInteractive {

    private final Material MATERIAL;

    public InteractiveItem(ItemTemplate it, Material m) {
        super(it);
        MATERIAL = m;
    }

    @Override
    public Material getMaterial() {
        return MATERIAL;
    }

    @Override
    public double getSelfDamageMultiplier() {
        return 1.0;
    }

    @Override
    public boolean doesDamageSelf() {
        //if this item interacts, it should default to taking the damage itself.
        //override if the item is innate, and should damage its actor instead
        return true;
    }

    @Override
    public boolean damageSelf(int amount) {
        //if this item interacts, it should default to not being destroyed.
        //override if this item can actually take damage.
        return true;
    }
}
