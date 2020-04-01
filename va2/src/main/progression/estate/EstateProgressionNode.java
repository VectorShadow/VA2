package main.progression.estate;

import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;

import java.io.Serializable;
import java.util.ArrayList;

public class EstateProgressionNode implements Serializable {
    private final String NAME;
    private final ArrayList<ResourceRequirement> RESOURCE_REQUIREMENTS;
    private final ArrayList<NodeIndex> PREREQUISITE_NODES;

    private int[] resourceIndex;
    private boolean unlocked = false;

    public EstateProgressionNode(String name, ArrayList<ResourceRequirement> resourceReqs, ArrayList<NodeIndex> prereqNodes) {
        NAME = name;
        RESOURCE_REQUIREMENTS = resourceReqs;
        PREREQUISITE_NODES = prereqNodes;
    }

    public boolean shouldDisplay(EstateProgression ep) {
        for (NodeIndex ni : PREREQUISITE_NODES) {
            if (!ep.nodeAt(ni).isUnlocked()) return false; //if any prereq node is not unlocked, we cannot unlock
        }
        return true;
    }

    public boolean canUnlock(Inventory inv, EstateProgression ep) {
        if (!shouldDisplay(ep)) //check prereq nodes
            return false;
        resourceIndex = new int[RESOURCE_REQUIREMENTS.size()];
        for (int i = 0; i < RESOURCE_REQUIREMENTS.size(); ++i) { //make sure all resource requirements are met
            boolean met = false;
            for (int j = 0; j < inv.size(); ++j) {
                ResourceRequirement rr = RESOURCE_REQUIREMENTS.get(i);
                ItemSlot is = inv.get(j);
                if (rr.isMet(is.peekItem(), is.count())) {
                    met = true;
                    resourceIndex[i] = j;
                    break; //don't check any more slots
                }
            }
            if (!met) return false; //if no item slot was able to meet this req, we cannot unlock
        }
        return true;
    }
    public void unlock(Inventory inv) {
        if (RESOURCE_REQUIREMENTS.size() != resourceIndex.length)
            throw new IllegalStateException("Resource index mismatch");
        for (int i = 0; i < RESOURCE_REQUIREMENTS.size(); ++i) {
            inv.remove(resourceIndex[i], RESOURCE_REQUIREMENTS.get(i).getCount());
        }
        unlocked = true;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public String toString() {
        String s = NAME + "(";
        for (int i = 0; i < RESOURCE_REQUIREMENTS.size(); ++i) {
            s += ((i > 0 ? "; " : "") + RESOURCE_REQUIREMENTS.get(i));
        }
        return s + ")";
    }
}
