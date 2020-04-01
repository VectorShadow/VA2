package world.item;

import world.WorldObject;
import world.item.Item;
import world.item.ItemTemplate;

/**
 * All items which of which an inventory can keep track of multiple copies in a single slot
 * will extend this class. This included all resources.
 * 
 * While some stackable items will use durability as a measure of capacity, this can never degrade,
 * thus this class forms a separate branch of the item tree from DegradableItem.
 */
public abstract class StackableItem extends Item {
    public StackableItem(ItemTemplate it) {
        super(it);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof StackableItem) && getTemplate().equals(((WorldObject) obj).getTemplate());
    }
}
