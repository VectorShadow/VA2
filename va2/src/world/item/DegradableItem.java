package world.item;

/**
 * This class specifies all items which can degrade for any reason, whether in combat or via burning fuel.
 *
 * Note that not all DegradeableItems necessarily degrade, but since the ones this mostly concerns, weapons and armor,
 * must implement contact interactivity which requires a material, we implement that field at this level to avoid
 * problems due to lack of multiple inheritance in Java(which previous implementation approaches suffered from).
 */
public abstract class DegradableItem extends Item {
    protected final boolean DOES_DEGRADE;
    private int durability;

    public DegradableItem(ItemTemplate it, boolean doesDegrade) {
        super(it);
        DOES_DEGRADE = doesDegrade;
        durability = it.DURABILITY;
    }
    DegradableItem(DegradableItem di) {
        this(((ItemTemplate)di.getTemplate()), di.DOES_DEGRADE);
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
    public int getDurability() {
         return durability;
    }

    public double getDurabilityPercent() {
        return (double)durability / (double) getMaxDurability();
    }
    /**
     * @param amount how much of this item's damage is to be repaired
     */
    public void repair(int amount) {
        durability += amount;
        if (durability > getMaxDurability()) durability = getMaxDurability();
    }
    private int getMaxDurability() {
        return ((ItemTemplate) getTemplate()).DURABILITY;
    }

    @Override
    public double getIntegrityPercent() {
        return getDurabilityPercent();
    }
}
