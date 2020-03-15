package world.item;

import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;

public class ItemTemplate extends WorldObjectTemplate {
    final int DURABILITY; //the maximum durability of this item. also used for fuel or other "charged" items

    public ItemTemplate(String d, String n, BalancedGlyphTemplate bgt, int dur) {
        super(d, n, bgt, false);
        DURABILITY = dur;
    }
}
