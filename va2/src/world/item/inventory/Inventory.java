package world.item.inventory;

import world.item.Item;
import world.item.StackableItem;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Store a list of items.
 */
public class Inventory implements Iterable<ItemSlot>, Serializable {
    private LinkedList<ItemSlot> itemSlots;
    int capacity;

    public Inventory(int cap) {
        capacity = cap;
        itemSlots = new LinkedList<>();
    }

    public Inventory() {
        this(-1);
    }

    public void setCapacity(int i) {
        capacity = i;
    }
    public void add(Item i){
        add(i, 1);
    }

    /**
     * Add the specified number of items to the inventory.
     * Return false if not all items could be added because of a capacity limit.
     */
    public boolean add(Item i, int count) {
        int index = find(i.getID()); //find where this item should go
        ItemSlot itemSlot;
        int addLimit;
        if (itemSlots.size() == 0 || index < 0) { //either this is our first item, or it should go before the first
            itemSlots.add(++index, ItemSlot.make(i)); //put it in the first place
            if (i instanceof StackableItem) {
                itemSlot = itemSlots.get(index);
                addLimit = capacity < 0 ? count - 1 : Math.min(count - 1, capacity - itemSlot.count()); //respect capacity
                itemSlot.addItem(addLimit); //add the rest to this slot
                return (addLimit == count - 1);
            } else {
                while (--count > 0)
                    itemSlots.add(++index, ItemSlot.make(i)); //add the rest to new slots at the front of the list
            }
        } else {
            itemSlot = itemSlots.get(index); //get the slot there
            if (i instanceof StackableItem) //if it stacks
                if (itemSlot.peekID() == i.getID()) {//if the ID is the same
                    addLimit = capacity < 0 ? count : Math.min(count, capacity - itemSlot.count()); //respect capacity
                    itemSlot.addItem(addLimit); //add the full count to this slot
                    return (addLimit == count - 1);
                } else {
                    itemSlots.add(++index, ItemSlot.make(i)); //otherwise insert a new slot for this item
                    itemSlots.get(index).addItem(count - 1); //and add the remaining count to it
                }
            else {
                while (count-- > 0)
                    itemSlots.add(++index, ItemSlot.make(i)); //otherwise add new slots until we run out of items
            }
        }
        return true;
    }
    public Item remove(int index) {
        return remove(index, 1);
    }
    public Item remove(int index, int count) {
        ItemSlot is = itemSlots.get(index);
        Item i = is.removeItem(count);
        if (is.count() == 0) itemSlots.remove(index);
        return i;
    }

    /**
     * Find the index where the specified itemID should or does exist in O(log(n)).
     */
    public int find(int itemID) {
        int lowerLimit = 0;
        int upperLimit = itemSlots.size() - 1;
        int searchIndex = upperLimit / 2;
        int indexedID;
        while(true) {
            if (searchIndex > itemSlots.size() - 1) return itemSlots.size() - 1;
            indexedID = itemSlots.get(searchIndex).peekItem().getID();
            if (searchIndex == 0 && indexedID > itemID) return -1;
            if (indexedID > itemID) {
                upperLimit = searchIndex;
                searchIndex = Math.min(searchIndex - 1, (searchIndex + lowerLimit) / 2);
            } else if (indexedID < itemID) {
                lowerLimit = searchIndex;
                searchIndex = Math.max(searchIndex + 1, (searchIndex + upperLimit) / 2);
            } else break;
            //target slot does not exist but is between the limits
            if (upperLimit - lowerLimit < 2
                    && itemSlots.get(lowerLimit).peekItem().getID() < itemID
                    && itemSlots.get(upperLimit).peekItem().getID() > itemID)
                return lowerLimit;
        }
        return searchIndex;
    }

    @Override
    public Iterator<ItemSlot> iterator() {
        return itemSlots.iterator();
    }

    public int size() {
        return itemSlots.size();
    }
    public ItemSlot get(int index) {
        return itemSlots.get(index);
    }

    @Override
    public String toString() {
        if (itemSlots.size() == 0) return "<empty>";
        String s = "";
        for (ItemSlot is : itemSlots)
            s += "\n" + is;
        return s;
    }
}
