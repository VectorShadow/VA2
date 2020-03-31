package main.progression;

import world.item.Item;
import world.item.Resource;

import java.io.Serializable;

public class ResourceRequirement implements Serializable {
    private final int COUNT;
    private final Resource RESOURCE;

    public ResourceRequirement(int c, Resource r) {
        COUNT = c;
        RESOURCE = r;
    }
    public boolean isMet(Item i, int c) {
        return i instanceof Resource && i.equals(RESOURCE) && c >= COUNT;
    }

    public int getCount() {
        return COUNT;
    }
}
