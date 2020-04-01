package world.item.inventory;

import world.item.Item;
import world.item.ItemDefinitions;
import world.item.StackableItem;

public class StackedItemSlot extends AbstractItemSlot implements ItemSlot {

    private final int itemID;
    private int count = 0;

    public StackedItemSlot(StackableItem si) {
        this(si, 1);
    }

    public StackedItemSlot(StackableItem si, int amount) {
        itemID = si.getID();
        count = amount;
    }

    @Override
    public Item peekItem() {
        return ItemDefinitions.get(itemID).clone();
    }

    @Override
    public int peekID() {
        return itemID;
    }

    @Override
    public Item removeItem(int amount) {
        if (count < amount)
            throw new IllegalArgumentException("Amount to remove " + amount + " exceeds count " + count());
        count -= amount;
        return peekItem();
    }

    @Override
    public void addItem(int amount) {
        count += amount;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public int compareTo(Integer o) {
        return itemID - o;
    }
}
