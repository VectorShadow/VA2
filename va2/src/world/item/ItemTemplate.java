package world.item;

import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;

public class ItemTemplate extends WorldObjectTemplate {
    public ItemTemplate(String d, String n, BalancedGlyphTemplate bgt) {
        super(d, n, bgt, false);
    }
}
