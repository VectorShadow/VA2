package world.item.inventory;

import world.item.DegradableItem;
import world.item.Item;

public class SingleItemSlot implements ItemSlot {

    private final DegradableItem item;
    private boolean inUse = true;

    public SingleItemSlot(DegradableItem di) {
        item = di;
    }

    @Override
    public Item peekItem() {
        return inUse ? item : null;
    }

    @Override
    public int peekID() {
        return item.getID();
    }

    @Override
    public Item removeItem(int amount) {
        if (amount != 1)
            throw new IllegalArgumentException("Amount to remove " + amount + " exceeds count " + count());
        inUse = false;
        return item;
    }

    @Override
    public void addItem(int amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int count() {
        return inUse ? 1 : 0;
    }

    @Override
    public int compareTo(Integer o) {
        return item.getID() - o;
    }
}
