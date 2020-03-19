package world.item;

import world.item.material.Material;

/**
 * This class specifies all items which can degrade for any reason, whether in combat or via burning fuel.
 * Material specifies either the material the combat item is made of, for combat interactions, or the material
 * it consumes as fuel (or both, since combat items can be repaired via the material they are made of).
 *
 * Note that not all DegradeableItems necessarily degrade, but since the ones this mostly concerns, weapons and armor,
 * must implement contact interactivity which requires a material, we implement that field at this level to avoid
 * problems due to lack of multiple inheritance in Java(which previous implementation approaches suffered from).
 */
public class DegradableItem extends Item {
    protected final boolean DOES_DEGRADE;
    private int durability;
    private final Material MATERIAL;

    public DegradableItem(ItemTemplate it, boolean doesDegrade, Material m) {
        super(it);
        DOES_DEGRADE = doesDegrade;
        durability = it.DURABILITY;
        MATERIAL = m;
    }
    DegradableItem(DegradableItem di) {
        this(((ItemTemplate)di.getTemplate()), di.DOES_DEGRADE, di.MATERIAL);
    }

    /**
     * ContactInteractive, implemented at ContactInteractiveItem and below, simply uses this method.
     */
    public Material getMaterial() {
        return MATERIAL;
    }

    /**
     * @param amount how much damage this item takes
     * @return whether this item has any durability left
     */
    public boolean damage(int amount) {
        if (!DOES_DEGRADE) return true;
        durability -= amount;
        return durability > 0;
    }

    /**
     * @return how damaged this item is
     */
    public int deficit() {
         return  maxDurability() - durability;
    }

    public double getDurabilityPercent() {
        return 100.0 * (double)durability / (double)maxDurability();
    }
    /**
     * @param amount how much of this item's damage is to be repaired
     */
    public void repair(int amount) {
        durability += amount;
        if (durability > maxDurability()) durability = maxDurability();
    }
    private int maxDurability() {
        return ((ItemTemplate)getTemplate()).DURABILITY;
    }

    @Override
    public DegradableItem clone() {
        return new DegradableItem(this);
    }

    @Override
    public double getIntegrityPercent() {
        return getDurabilityPercent();
    }
}
