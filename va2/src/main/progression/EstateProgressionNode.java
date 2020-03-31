package main.progression;

import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;

import java.util.ArrayList;

public class EstateProgressionNode {
    private boolean unlocked = false;
    private ArrayList<ResourceRequirement> resourceRequirements;
    private ArrayList<NodeIndex> prerequisiteNodes;

    public boolean canUnlock(Inventory i, EstateProgression ep) {
        //todo - build a list of inventory indices corresponding to met resource requirements
        // if we return true, unlock can simply use that list rather than finding them all again
        for (ResourceRequirement rr : resourceRequirements) { //make sure all resource requirements are met
            boolean met = false;
            for (ItemSlot is : i) {
                if (rr.isMet(is.peekItem(), is.count())) {
                    met = true;
                    break; //don't check any more slots
                }
            }
            if (!met) return false; //if no item slot was able to meet this req, we cannot unlock
        }
        for (NodeIndex ni : prerequisiteNodes) {
            if (!ep.nodeAt(ni).isUnlocked()) return false; //if any prereq node is not unlocked, we cannot unlock
        }
        return true;
    }
    public void unlock() {
        //todo - use the list built during canUnlock.
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}
