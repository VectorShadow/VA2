package world.item.inventory;

import combat.armor.ArmorDefinitions;
import combat.melee.weapons.MeleeWeaponDefinitions;
import error.ErrorLogger;
import world.item.Item;
import world.item.ItemDefinitions;
import world.item.StackableItem;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Store a list of items.
 */
public class Inventory implements Iterable<ItemSlot> {
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
                    addLimit = capacity < 0 ? count - 1 : Math.min(count - 1, capacity - itemSlot.count()); //respect capacity
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
            if (searchIndex <= 0) return -1;
            System.out.println("Search index: " + searchIndex);
            indexedID = itemSlots.get(searchIndex).peekItem().getID();
            if (indexedID > itemID) {
                upperLimit = searchIndex;
                searchIndex = (searchIndex + lowerLimit) / 2;
            } else if (indexedID < itemID) {
                lowerLimit = searchIndex;
                searchIndex = ((searchIndex + upperLimit) / 2) + 1;
            } else break;
        }
        return searchIndex;
    }

    public static void test() {
        Inventory i = new Inventory(55);
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.BRONZE_SHORT_SWORD().getID()), 5);
        i.add(ItemDefinitions.get(ArmorDefinitions.LEATHER_VEST().getID()));
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.BRONZE_SHORT_SWORD().getID()));
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.SPIDER_BITE().getID()));
        i.add(ItemDefinitions.get(0xf101));
        i.add(ItemDefinitions.get(0xf100), 99);
        i.remove(1);
        i.remove(0, 36);
        i.remove(5);
        i.setCapacity(1024);
        System.out.println(i.add(ItemDefinitions.get(0xf102), 77));
        for (ItemSlot is : i.itemSlots)
            System.out.println(is.peekItem().getTemplate().getName() + " x" + is.count());
        try {
            i.remove(5, 3);
            ErrorLogger.logFatalException(new IllegalStateException("IllegalArgumentException expected!"));
        } catch (IllegalArgumentException iae) {}
    }

    @Override
    public Iterator<ItemSlot> iterator() {
        return itemSlots.iterator();
    }
}
