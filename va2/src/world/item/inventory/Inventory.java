package world.item.inventory;

import combat.armor.ArmorDefinitions;
import combat.melee.weapons.MeleeWeaponDefinitions;
import error.ErrorLogger;
import world.item.Item;
import world.item.ItemDefinitions;
import world.item.StackableItem;

import java.util.LinkedList;

/**
 * Store a list of items.
 */
public class Inventory {
    private LinkedList<ItemSlot> itemSlots;

    public Inventory() {
        itemSlots = new LinkedList<>();
    }

    public void add(Item i){
        add(i, 1);
    }

    /**
     * Add the specified number of items to the inventory.
     */
    public void add(Item i, int count) {
        int index = find(i.getID()); //find where this item should go
        if (itemSlots.size() == 0 || index < 0) { //either this is our first item, or it should go before the first
            itemSlots.add(++index, ItemSlot.make(i)); //put it in the first place
            if (i instanceof StackableItem)
                itemSlots.get(index).addItem(count - 1); //add the rest to this slot
            else {
                while (--count > 0)
                    itemSlots.add(++index, ItemSlot.make(i)); //add the rest to new slots at the front of the list
            }
        } else {
            ItemSlot itemSlot = itemSlots.get(index); //get the slot there
            if (i instanceof StackableItem) //if it stacks
                if (itemSlot.peekID() == i.getID()) //if the ID is the same
                    itemSlot.addItem(count); //add the full count to this slot
                else {
                    itemSlots.add(++index, ItemSlot.make(i)); //otherwise insert a new slot for this item
                    itemSlots.get(index).addItem(count - 1); //and add the remaining count to it
                }
            else {
                while (count-- > 0)
                    itemSlots.add(++index, ItemSlot.make(i)); //otherwise add new slots until we run out of items
            }
        }
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
        Inventory i = new Inventory();
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.BRONZE_SHORT_SWORD().getID()), 5);
        i.add(ItemDefinitions.get(ArmorDefinitions.LEATHER_VEST().getID()));
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.BRONZE_SHORT_SWORD().getID()));
        i.add(ItemDefinitions.get(MeleeWeaponDefinitions.SPIDER_BITE().getID()));
        i.add(ItemDefinitions.get(0xf101));
        i.add(ItemDefinitions.get(0xf100), 99);
        i.remove(1);
        i.remove(0, 36);
        i.remove(5);
        for (ItemSlot is : i.itemSlots)
            System.out.println(is.peekItem().getTemplate().getName() + " x" + is.count());
        try {
            i.remove(5, 3);
            ErrorLogger.logFatalException(new IllegalStateException("IllegalArgumentException expected!"));
        } catch (IllegalArgumentException iae) {}
    }
}
