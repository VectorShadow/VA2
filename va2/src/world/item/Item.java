package world.item;

import world.WorldObject;
import world.item.material.Material;

public abstract class Item extends WorldObject {

    public Item(ItemTemplate it) {
        super(it);
    }

    @Override
    public abstract Item clone();

    public abstract double getIntegrityPercent();

    /**
     * ContactInteractive, implemented at ContactInteractiveItem and below, simply uses this method.
     */
    public Material getMaterial() {
        return ((ItemTemplate) getTEMPLATE()).MATERIAL;
    }

    public ItemQuality getQuality() {
        return ((ItemTemplate) getTEMPLATE()).QUALITY;
    }
}
