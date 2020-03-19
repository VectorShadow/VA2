package world.item;

import world.WorldObject;

public abstract class Item extends WorldObject {

    public Item(ItemTemplate it) {
        super(it);
    }

    @Override
    public abstract Item clone();

    public abstract double getIntegrityPercent();

    public ItemQuality getQuality() {
        return ((ItemTemplate)getTemplate()).QUALITY;
    }
}
