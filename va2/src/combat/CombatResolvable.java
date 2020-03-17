package combat;

import main.extensible.GlyphDisplayable;
import world.item.InteractiveItem;

public abstract class CombatResolvable extends GlyphDisplayable {
    private final InteractiveItem INTERACTIVE_ITEM;

    public CombatResolvable(InteractiveItem i) {
        super(i.getTemplate().getDescription(), i.getTemplate().getName(), i.getTemplate().getBalancedGlyphTemplate());
        INTERACTIVE_ITEM = i;
    }

    public InteractiveItem getInteractiveItem() {
        return INTERACTIVE_ITEM;
    }
}
