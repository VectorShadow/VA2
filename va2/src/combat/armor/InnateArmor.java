package combat.armor;

import main.extensible.TextDisplayable;

import java.awt.*;

/**
 * This class specifies a WornArmor that is not derived from an item.
 * It must still be displayable as text, but need not have a Glyph.
 */
public class InnateArmor extends TextDisplayable implements WornArmor {

    private final ResolvableArmor RESOLVABLE_ARMOR;

    public InnateArmor(String d, String n, Color dc, ResolvableArmor ra) {
        super(d, n, dc);
        RESOLVABLE_ARMOR = ra;
    }

    @Override
    public ResolvableArmor getResolvableArmor() {
        return RESOLVABLE_ARMOR;
    }
}
