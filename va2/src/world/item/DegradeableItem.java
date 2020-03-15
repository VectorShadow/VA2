package world.item;

public abstract class DegradeableItem extends Item {
    private int durability;

    public DegradeableItem(ItemTemplate it) {
        super(it);
        durability = it.DURABILITY;
    }

    /**
     * @param amount how much damage this item takes
     * @return whether this item has any durability left
     */
    public boolean damage(int amount) {
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
}
