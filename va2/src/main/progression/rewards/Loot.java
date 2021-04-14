package main.progression.rewards;

import world.dungeon.theme.ThemeDefinitions;
import world.item.Item;
import world.item.define.ItemDefinitions;
import world.item.StackableItem;
import world.item.inventory.ItemSlot;

import java.util.Iterator;

public class Loot {

    public static final int ALL_FAMILIES = -1;

    public static final DropTable LEGACY = generateDropTable(ThemeDefinitions.ANY, Item.QUALITY_MUNDANE, Item.FAMILY_LEGACY_RESOURCE, 0.33);

    public static DropTable generateDropTable(int theme, int shiftedQualityBias, int itemFamily, double noDropChance) {
        DropTable table = new DropTable(noDropChance);
        int qualityBias = Item.unshiftQuality(shiftedQualityBias);
        for (Iterator<Item> itemIterator = ItemDefinitions.iterator(); itemIterator.hasNext();) {
            Item item = itemIterator.next();
            int quality = Item.unshiftQuality(item.getItemQuality());
            //if we're not accepting all families and we've moved beyond the desired family, we are done
            if (itemFamily > ALL_FAMILIES && item.getItemFamily() != itemFamily) break;
            //continue if this item is not of the desired theme(and a specific theme is desired)
            if ((item.getThemeIndex() != theme && item.getThemeIndex() != ThemeDefinitions.ANY && theme != ThemeDefinitions.ANY) ||
                    //or if the item is from the Estate theme - this corresponds to player made items
                    (item.getThemeIndex() == ThemeDefinitions.YSIAN_ESTATE) ||
                    //or if we come across an innate item - these may not be dropped
                    (quality == Item.QUALITY_INNATE)) continue;
            table.add(item, 1, baseChance(quality, qualityBias));
            if (item instanceof StackableItem) {
                /*Stackable items have a chance to drop stacks.
                  We double adjusted quality of the drop each time:
                    MUNDANE(1) -> COMMON(8) -> EXOTIC(32) -> LEGENDARY(512),
                    COMMON(1) -> EXOTIC(8) -> LEGENDARY(32),
                    SCARCE(1) -> HEROIC(8) -> ULTIMATE(32),
                    EXOTIC(1) -> LEGENDARY(8),
                    RARE(1) -> FABLED(8),
                    HEROIC(1) -> ULTIMATE(8)
                 */
                for (int adjustedQuality = quality * 2; adjustedQuality < Item.unshiftQuality(Item.QUALITY_ULTIMATE); adjustedQuality *= 2)
                    table.add(item, (int)(Math.pow(2, (adjustedQuality / quality) + 1)), baseChance(adjustedQuality, qualityBias));
            }
        }
        //todo - lots here.
        return table;
    }
    private static double baseChance(int itemQuality, int qualityBias) {
        double power = Math.abs(itemQuality - qualityBias);
        return Math.pow(2.0, - (power + 4));
    }

    public static void test() {
        Reward r = new Reward(0, 1, LEGACY);
        ItemSlot is;
        for (int i = 0; i < 32; ++i) {
            is = r.finalize(null).rollDrop().get(0);
            System.out.println("Drop: " + (is == null ? " nothing " : is.count() + "x " + is.peekItem().getTemplate().getName()));
        }
    }
}
