package main.progression.estate;

import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;

import java.io.Serializable;
import java.util.ArrayList;

public class EstateProgressionNode implements Serializable {
    private boolean unlocked = false;
    private ArrayList<ResourceRequirement> resourceRequirements;
    private ArrayList<NodeIndex> prerequisiteNodes;
    private int[] resourceIndex;

    public boolean canUnlock(Inventory inv, EstateProgression ep) {
        resourceIndex = new int[resourceRequirements.size()];
        for (int i = 0; i < resourceRequirements.size(); ++i) { //make sure all resource requirements are met
            boolean met = false;
            for (int j = 0; j < inv.size(); ++j) {
                ResourceRequirement rr = resourceRequirements.get(i);
                ItemSlot is = inv.get(j);
                if (rr.isMet(is.peekItem(), is.count())) {
                    met = true;
                    resourceIndex[i] = j;
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
    public void unlock(Inventory inv) {
        if (resourceRequirements.size() != resourceIndex.length)
            throw new IllegalStateException("Resource index mismatch");
        for (int i = 0; i < resourceRequirements.size(); ++i) {
            inv.remove(resourceIndex[i], resourceRequirements.get(i).getCount());
        }
        unlocked = true;
    }

    public boolean isUnlocked() {
        return unlocked;
    }
}
