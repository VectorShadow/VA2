package world.item;

import main.Session;
import resources.chroma.Chroma;
import resources.glyph.BalancedGlyphTemplate;
import resources.glyph.GlyphString;
import util.ArrayListBuilder;
import world.WorldObject;
import world.item.material.Material;

import java.awt.*;
import java.util.ArrayList;

public abstract class Item extends WorldObject {
    /**
     * Constants for specifying loot items.
     * ITEM_FAMILY specifies the classification of the item.
     * ITEM_THEME specifies the theme in which the item can be found - 0 indicates an item which may not be generated,
     * such as innate items, and f indicates an item which can appear in any theme.
     * ITEM_QUALITY specifies the quality level of the item, which generally also corresponds to rarity.
     * UNIQUE_ID differentiates items for all others with the same non-unique ID.
     */

    public static final int ITEM_FAMILY = 0x00ff_0000;
    public static final int ITEM_THEME = 0x0000_f000;
    public static final int ITEM_QUALITY = 0x0000_0f00;
    public static final int UNIQUE_ID = 0x0000_00ff;

    public static final int FAMILY_LEGACY_RESOURCE = 0x0000_0000;
    public static final int FAMILY_TRANSIENT_RESOURCE = 0x0001_0000;
    public static final int FAMILY_MELEE_WEAPON = 0x0002_0000;
    public static final int FAMILY_ARMOR = 0x0003_0000;
    public static final int FAMILY_TEXT = 0x0004_0000;
    //todo - many more
    public static final int QUALITY_INNATE = 0x0000_0000;
    public static final int QUALITY_MUNDANE = 0x0000_0100;
    public static final int QUALITY_COMMON = 0x0000_0200;
    public static final int QUALITY_SCARCE = 0x0000_0300;
    public static final int QUALITY_EXOTIC = 0x0000_0400;
    public static final int QUALITY_RARE = 0x0000_0500;
    public static final int QUALITY_HEROIC = 0x0000_0600;
    public static final int QUALITY_EPIC = 0x0000_0700;
    public static final int QUALITY_LEGENDARY = 0x0000_0800;
    public static final int QUALITY_MYTHIC = 0x0000_0900;
    public static final int QUALITY_FABLED = 0x0000_0a00;
    public static final int QUALITY_TRANSCENDENT = 0x0000_0b00;
    public static final int QUALITY_ULTIMATE = 0x0000_0c00;

    public static int shiftTheme(int themeIndex) {
        return themeIndex << 12;
    }
    public static int unshiftQuality(int idQuality) {
        return idQuality >> 8;
    }
    public Item(ItemTemplate it) {
        super(it);
    }

    @Override
    public abstract Item clone();

    public abstract double getIntegrityPercent();

    /**
     * ContactInteractive, implemented at ContactInteractiveItem and below, simply uses this method.
     */
    public Material getMaterial() {
        return ((ItemTemplate) getTemplate()).MATERIAL;
    }

    public int getID() {
        return ((ItemTemplate)getTemplate()).getItemID();
    }
    public int getItemFamily() {
        return getID() & ITEM_FAMILY;
    }
    public int getThemeIndex() {
        return (getID() & ITEM_THEME) >> 12;
    }
    public int getItemQuality() {
        return getID() & ITEM_QUALITY;
    }
    protected GlyphString getQualityColoredName() {
        return new GlyphString(getTemplate().getName(), getQualityColor(getItemQuality()));
    }
    private BalancedGlyphTemplate getQualityColor(int qualityLevel) {
        ArrayList<Color> foregrounds = new ArrayList<>();
        switch (qualityLevel) {
            //innate
            case QUALITY_INNATE: foregrounds.add(Chroma.WHITE); break;
            //mundane
            case QUALITY_MUNDANE: foregrounds.add(Chroma.GREY); break;
            //common
            case QUALITY_COMMON: foregrounds.add(Chroma.BROWN); break;
            //scarce
            case QUALITY_SCARCE: foregrounds.add(Chroma.GREEN); break;
            //exotic
            case QUALITY_EXOTIC: foregrounds.add(Chroma.BLUE); break;
            //rare
            case QUALITY_RARE: foregrounds.add(Chroma.RED); break;
            //heroic
            case QUALITY_HEROIC: foregrounds.add(Chroma.SLIME); foregrounds.add(Chroma.AQUA); break;
            //epic
            case QUALITY_EPIC: foregrounds.add(Chroma.VIOLET); foregrounds.add(Chroma.TURQUOISE); break;
            //legendary
            case QUALITY_LEGENDARY: foregrounds.add(Chroma.ORANGE); foregrounds.add(Chroma.CRIMSON); break;
            //mythic
            case QUALITY_MYTHIC:
                foregrounds.add(Chroma.YELLOW);
                foregrounds.add(Chroma.MAGENTA);
                foregrounds.add(Chroma.CYAN);
                break;
            //fabled
            case QUALITY_FABLED:
                foregrounds.add(Chroma.GREEN);
                foregrounds.add(Chroma.BLUE);
                foregrounds.add(Chroma.RED);
                foregrounds.add(Chroma.SLIME);
                foregrounds.add(Chroma.AQUA);
                foregrounds.add(Chroma.VIOLET);
                foregrounds.add(Chroma.TURQUOISE);
                foregrounds.add(Chroma.ORANGE);
                foregrounds.add(Chroma.CRIMSON);
                break;
            //transcendent
            case QUALITY_TRANSCENDENT:
                foregrounds.add(Chroma.METALLIC_IRON);
                foregrounds.add(Chroma.METALLIC_BRONZE);
                foregrounds.add(Chroma.METALLIC_SILVER);
                foregrounds.add(Chroma.METALLIC_GOLD);
                foregrounds.add(Chroma.METALLIC_PLATINUM);
                break;
            //ultimate
            case QUALITY_ULTIMATE:
                foregrounds.add(Chroma.GREEN);
                foregrounds.add(Chroma.BLUE);
                foregrounds.add(Chroma.RED);
                foregrounds.add(Chroma.SLIME);
                foregrounds.add(Chroma.AQUA);
                foregrounds.add(Chroma.VIOLET);
                foregrounds.add(Chroma.TURQUOISE);
                foregrounds.add(Chroma.ORANGE);
                foregrounds.add(Chroma.CRIMSON);
                foregrounds.add(Chroma.YELLOW);
                foregrounds.add(Chroma.MAGENTA);
                foregrounds.add(Chroma.CYAN);
                foregrounds.add(Chroma.METALLIC_IRON);
                foregrounds.add(Chroma.METALLIC_BRONZE);
                foregrounds.add(Chroma.METALLIC_SILVER);
                foregrounds.add(Chroma.METALLIC_GOLD);
                foregrounds.add(Chroma.METALLIC_PLATINUM);
                break;
            default:
                throw new IllegalStateException("Unhandled Quality: " + qualityLevel);
        }
        return new BalancedGlyphTemplate(
                ArrayListBuilder.initialize().addElement(' ').build(),
                ArrayListBuilder.initialize().addElement(Session.getColorScheme().getBackground()).build(),
                foregrounds
        );
    }
}
