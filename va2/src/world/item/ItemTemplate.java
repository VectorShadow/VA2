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
    final int ITEM_ID; //the unique serial ID of this template.

    public ItemTemplate(String d, String n, BalancedGlyphTemplate bgt, int dur, Material m, int id) {
        super(d, n, bgt, false);
        DURABILITY = dur * m.getHardness();
        MATERIAL = m;
        ITEM_ID = id;
    }
    private ItemTemplate(ItemTemplate it) {
        this(
                it.DESCRIPTION,
                it.NAME,
                it.BALANCED_GLYPH_TEMPLATE.clone(),
                it.DURABILITY / it.MATERIAL.getHardness(),
                it.MATERIAL,
                it.ITEM_ID
        );
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

    public int getItemID() {
        return ITEM_ID;
    }

    @Override
    public ItemTemplate clone() {
        return new ItemTemplate(this);
    }
}
