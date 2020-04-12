package main.modes.menu.estate;

import world.item.inventory.Inventory;

/**
 * Used to handle the result of a selection from an inventory.
 * Implementations must define what is done with the item at the selected index, completely.
 * This includes removing it if necessary, as well as all other code needed for its purpose.
 */
public abstract class ItemSelectionExecutor {

    protected final Inventory INVENTORY;
    protected int selectedIndex = -1;

    public ItemSelectionExecutor(Inventory inventory) {
        INVENTORY = inventory;
    }


    public void select(int index) {
        selectedIndex = index;
    }

    /**
     *
     */
    public abstract void handleSelection();
}
