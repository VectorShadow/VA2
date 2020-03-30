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
        return i instanceof DegradableItem ? new SingleItemSlot((DegradableItem)i) : new StackedItemSlot((StackableItem)i);
    }
}
