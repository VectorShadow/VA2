package world.item.inventory;

import world.item.DegradableItem;
import world.item.Item;
import world.item.StackableItem;

public interface ItemSlot extends Comparable<Integer> {
    Item peekItem();
    int peekID();
    Item removeItem(int amount);
    void addItem(int amount);
    int count();


    static ItemSlot make(Item i) {
        return make(i, 1);
    }

    static ItemSlot make(Item i, int amount) {
        if (i instanceof DegradableItem && amount != 1)
            throw new IllegalArgumentException("cannot make SingleItemSlot with qty != 1");
        return i instanceof DegradableItem ? new SingleItemSlot((DegradableItem)i) : new StackedItemSlot((StackableItem)i, amount);
    }
}
