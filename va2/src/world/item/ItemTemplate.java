package world.item;

import resources.glyph.BalancedGlyphTemplate;
import world.WorldObjectTemplate;
import world.item.material.Material;
/**
 * Material specifies either the material the combat item is made of, for combat interactions, or the material
 * it consumes as fuel (or both, since combat items can be repaired via the material they are made of).
 */
public class ItemTemplate extends WorldObjectTemplate {
    final int DURABILITY; //the maximum durability of this item. also used for fuel or other "charged" items
    final Material MATERIAL; //the primary material this item is made from
    final ItemQuality QUALITY; //the quality classification of this item

    public ItemTemplate(String d, String n, BalancedGlyphTemplate bgt, int dur, Material m, ItemQuality iq) {
        super(d, n, bgt, false);
        DURABILITY = dur * m.getHardness();
        MATERIAL = m;
        QUALITY = iq;
    }

    public int getDurability() {
        return DURABILITY;
    }

    /**
     * ContactInteractive, implemented at ContactInteractiveItem and below, simply uses this method.
     */
    public Material getMaterial() {
        return MATERIAL;
    }

    public ItemQuality getQuality() {
        return QUALITY;
    }
}
