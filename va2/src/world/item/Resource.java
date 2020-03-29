package world.item;

/**
 * Legacy resources have Material of null,
 * Transient have a specific resource type(in fact this is their sole definition and purpose).
 */
public class Resource extends Item {

    public Resource(ItemTemplate it) {
        super(it);
    }
    private Resource(Resource r) {
        this((ItemTemplate)r.getTemplate());
    }

    public boolean isLegacy() {
        return getItemFamily() == Item.FAMILY_LEGACY_RESOURCE;
    }

    @Override
    public Item clone() {
        return new Resource(this);
    }

    @Override
    public double getIntegrityPercent() {
        return 1.0;
    }
}
