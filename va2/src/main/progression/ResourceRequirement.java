package main.progression;

import world.item.Item;
import world.item.Resource;

public class ResourceRequirement {
    private final int COUNT;
    private final Resource RESOURCE;

    public ResourceRequirement(int c, Resource r) {
        COUNT = c;
        RESOURCE = r;
    }
    public boolean isMet(Item i, int c) {
        return i instanceof Resource && i.equals(RESOURCE) && c >= COUNT;
    }
}
