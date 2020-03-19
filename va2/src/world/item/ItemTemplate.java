package world.item;

import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;

public class ItemTemplate extends WorldObjectTemplate {
    final int DURABILITY; //the maximum durability of this item. also used for fuel or other "charged" items
    final ItemQuality QUALITY; //the quality classification of this item

    public ItemTemplate(String d, String n, BalancedGlyphTemplate bgt, int dur, ItemQuality iq) {
        super(d, n, bgt, false);
        DURABILITY = dur;
        QUALITY = iq;
    }

    public int getDurability() {
        return DURABILITY;
    }

    public ItemQuality getQuality() {
        return QUALITY;
    }
}
