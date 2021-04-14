package world.item.define;

import combat.DamageType;
import combat.WeaponDamage;
import combat.melee.weapons.MeleeStyle;
import combat.melee.weapons.MeleeWeaponClass;
import main.Session;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import status.StatusType;
import util.ArrayListBuilder;
import world.WorldObjectTemplateFactory;
import world.item.*;
import world.item.inventory.ItemSlot;
import world.item.material.Material;
import world.item.material.MaterialDefinitions;
import world.lore.LoreDefinitions;

import java.util.Iterator;

import static world.item.Item.*;
import static world.dungeon.theme.ThemeDefinitions.*;
import static world.lore.language.Language.*;

/**
 * Define all items in one place.
 * Individual Item family definition files provide methods that access this array via the get() methdod.
 * Ordered by Family, then Theme, then Quality, then UniqueID.
 */
public class ItemDefinitions {

    public static final int DURABILITY_UNIT = 1_024;

    public static class AllItemIterator implements Iterator<Item> {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < ORDERED_ITEMS.length;
        }

        @Override
        public Item next() {
            return ORDERED_ITEMS[cursor++];
        }
    }
    private static final Item[] ORDERED_ITEMS = {
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Lumber")
                            .setDescription("common lumber")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_MUNDANE | 0x00)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Stone")
                            .setDescription("common stone")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_MUNDANE | 0x01)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Cloth")
                            .setDescription("rough cloth")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_MUNDANE | 0x02)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Simple tools")
                            .setDescription("simple tools")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_MUNDANE | 0x03)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Tile")
                            .setDescription("tile")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x00)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Parchment")
                            .setDescription("parchment")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x01)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Metalwork")
                            .setDescription("miscellaneous metal fixtures")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x02)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Glass")
                            .setDescription("blown glass")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x03)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Limestone")
                            .setDescription("limestone blocks")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x04)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Linen")
                            .setDescription("woven linen cloth")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x05)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Ink")
                            .setDescription("common black ink")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x06)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Common dyes")
                            .setDescription("dyes in common colors")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x07)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Specialized tools")
                            .setDescription("tools customized for specific uses")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_SCARCE | 0x08)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Gemstones")
                            .setDescription("miscellaneous precious and semi-precious gems")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x00)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Gilding")
                            .setDescription("finely worked golden trim")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x01)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Rare metals")
                            .setDescription("rare metals with special properties")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x02)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Crystal")
                            .setDescription("fine crystalline glass")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x03)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Incense")
                            .setDescription("rare and pungent incense")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x04)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Marble")
                            .setDescription("lustrous, richly veined marble blocks")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x05)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Silk")
                            .setDescription("a bolt of shimmering silken cloth")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x06)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Vellum")
                            .setDescription("specially fabricated paper of the highest quality")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x07)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Calligraphic ink")
                            .setDescription("specially formulated inks of the highest quality")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x08)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Rare dyes")
                            .setDescription("special dyes in rich colors")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x09)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Rare wood")
                            .setDescription("aromatic wood with special properties")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x0a)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("Mastercraft tools")
                            .setDescription("tools masterfully crafted for very specific purposes")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, null,
                                    FAMILY_LEGACY_RESOURCE | Item.shiftTheme(ANY) | QUALITY_RARE | 0x0b)
            ),
            //todo - additional legacy resources?
            /*
            Here we define scrap materials. Any item which is made of the same material will scrap into the corresponding
            transient resource. Note that individual themes may offer special materials for item construction, but all
            scrap must come from degradable. We may choose to offer junk items made of desired materials that the player
            will only want to use for scrap, in order to facilitate repair/construction of more desireable items.
             */
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("leather scrap")
                            .setDescription("leather scrap")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, MaterialDefinitions.SOFT_LEATHER,
                                    FAMILY_TRANSIENT_RESOURCE | Item.shiftTheme(YSIAN_ESTATE) | QUALITY_MUNDANE | 0x00)
            ),
            new Resource(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bronze scrap")
                            .setDescription("bronze scrap")
                            .setSymbols('~')
                            .manufactureItemTemplate(1, MaterialDefinitions.BRONZE,
                                    FAMILY_TRANSIENT_RESOURCE | Item.shiftTheme(YSIAN_ESTATE) | QUALITY_MUNDANE | 0x01)
            ),
            //todo - more scrap materials
            //todo - additional transient resources
            /*
             * Define all weapons for use in the game.
             * Note that weapons which have multiple WeaponDamage values within a continuum should place the most
             * powerful value as the base, since Heavy Blows will resolve to the base rather than rolling a random value.
             * int[] should boost accuracy for large weapons, precision for small, damage for special reasons
             */
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bare fists")
                            .setDescription("<unarmed>")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.FLESH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x00),
                    false,
                    true,
                    25,
                    10,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("punch&", 1.0, DamageType.IMPACT)
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("arachnid fangs")
                            .setDescription("spider bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.TOOTH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x01),
                    false,
                    true,
                    8,
                    3,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            ),

            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("canine maw")
                            .setDescription("wolf bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.TOOTH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x02),
                    false,
                    true,
                    15,
                    3,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("frothing canine maw")
                            .setDescription("rabid wolf bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.TOOTH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x03),
                    false,
                    true,
                    15,
                    3,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.75,
                                    new WeaponDamage(
                                            "frantically bite$",
                                            0.75,
                                            DamageType.PUNCTURE,
                                            StatusType.RABIES,
                                            0.5
                                    )
                            )).build()
                    )
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ursine paw")
                            .setDescription("bear swat")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.FLESH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x04),
                    false,
                    true,
                    30,
                    12,
                    .45,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("claw$", 1.25, DamageType.REND),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.66,
                                    new WeaponDamage("swat$", 0.9, DamageType.IMPACT))).build()
                    )
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ursine maw")
                            .setDescription("bear bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.TOOTH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x05),
                    false,
                    true,
                    22,
                    4,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("barbed chitinous stinger")
                            .setDescription("acidic sting")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.ANCIENT_CHITIN,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE + 0x06),
                    false,
                    true,
                    63,
                    15,
                    .1,
                    new int[] {0, 0, 0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("viciously sting$", 1.33, DamageType.ACID),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.8,
                                    new WeaponDamage("sting$", 0.75, DamageType.PUNCTURE))).build())
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("water moccasin fangs")
                            .setDescription("needle sharp fangs of a venomous snake")
                            .manufactureItemTemplate(1, MaterialDefinitions.TOOTH,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE + 0x07),
                    false,
                    true,
                    57,
                    22,
                    .25,
                    new int[]{0, 0, 0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE, StatusType.VENOM_0, 0.67)
                    )
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("heavy silken strand")
                            .setDescription("a sticky strand of heavy silk webbing")
                            .manufactureItemTemplate(1, MaterialDefinitions.SPIDER_SILK,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x08),
                    false,
                    false,
                    96,
                    32,
                    .33,
                    new int[]{5, 0, 0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("throw$ a silken web at", 1.0, DamageType.IMPACT, StatusType.WEBBED, 0.15)
                    )
            ),
            //todo - more innate melee weapons!
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bronze short sword")
                            .setDescription("a short straight sword made of bronze")
                            .manufactureItemTemplate(DURABILITY_UNIT * 16, MaterialDefinitions.BRONZE,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(YSIAN_ESTATE) | QUALITY_MUNDANE | 0x00),
                    true,
                    false,
                    48,
                    32,
                    .33,
                    new int[] {0, 2, 0},
                    MeleeStyle.ONE_HAND,
                    MeleeWeaponClass.SHORT_BLADES,
                    new Continuum<>(
                            new WeaponDamage("slash&", 1.0, DamageType.REND),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.33,
                                    new WeaponDamage("stab", 1.0, DamageType.PUNCTURE))).build()
                    )
            ),
            //todo - more player made melee weapons!
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ancient stone spear")
                            .setDescription("a crude ancient spear with a stone head")
                            .manufactureItemTemplate(DURABILITY_UNIT * 12, MaterialDefinitions.COMMON_STONE,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(DARK_GROVE) | QUALITY_MUNDANE | 0x00),
                    true,
                    false,
                    36,
                    12,
                    .25,
                    new int[] {1, 0, 0},
                    MeleeStyle.TWO_HAND,
                    MeleeWeaponClass.POLE,
                    new Continuum<>(
                            new WeaponDamage("stab", 1.0, DamageType.PUNCTURE)
                    )
            ),
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ancient bronze hatchet")
                            .setDescription("an ancient bronze cutting tool")
                            .manufactureItemTemplate(DURABILITY_UNIT * 12, MaterialDefinitions.BRONZE,
                                    FAMILY_MELEE_WEAPON | Item.shiftTheme(DARK_GROVE) | QUALITY_COMMON | 0x1),
                    true,
                    false,
                    42,
                    12,
                    .42,
                    new int[] {0, 1, 0},
                    MeleeStyle.ONE_HAND,
                    MeleeWeaponClass.AXE,
                    new Continuum<>(
                            new WeaponDamage("hack$", 1.0, DamageType.REND)
                    )
            ),
            //todo - more theme weapon drops
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("<no armor>")
                            .setDescription("<no armor>")
                            .manufactureItemTemplate(1, MaterialDefinitions.FLESH,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x00),
                    false,
                    true,
                    0.0,
                    0
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("arachnid exoskeleton")
                            .setDescription("the natural armor of a spider")
                            .manufactureItemTemplate(1, MaterialDefinitions.CHITIN,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x01),
                    false,
                    true,
                    0.92,
                    3
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ancient arachnid exoskeleton")
                            .setDescription("the natural armor of an ancient spider")
                            .manufactureItemTemplate(1, MaterialDefinitions.ANCIENT_CHITIN,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x02),
                    false,
                    true,
                    0.92,
                    8
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("scale covered snakeskin")
                            .setDescription("the natural armor of a serpent")
                            .manufactureItemTemplate(1, MaterialDefinitions.SNAKESKIN,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x03),
                    false,
                    true,
                    1,
                    5
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("heavy spidersilk cocoon")
                            .setDescription("a mummy-like wrapping of thick spider silk")
                            .manufactureItemTemplate(1, MaterialDefinitions.SPIDER_SILK,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x04),
                    false,
                    true,
                    0.95,
                    10
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("wolf hide and fur")
                            .setDescription("wolf skin covered in thick fur")
                            .manufactureItemTemplate(1, MaterialDefinitions.BEAST_HIDE_AND_FUR,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x05),
                    false,
                    true,
                    0.8,
                    6
            ),
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bear hide and fur")
                            .setDescription("bear fat and skin covered in thick fur")
                            .manufactureItemTemplate(1, MaterialDefinitions.BEAST_HIDE_AND_FUR,
                                    FAMILY_ARMOR | Item.shiftTheme(INNATE) | QUALITY_INNATE | 0x06),
                    false,
                    true,
                    0.8,
                    9
            ),
            //todo - more innate armors!
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("leather vest")
                            .setDescription("a protective vest of supple leather")
                            .manufactureItemTemplate(DURABILITY_UNIT * 8, MaterialDefinitions.SOFT_LEATHER,
                                    FAMILY_ARMOR | Item.shiftTheme(YSIAN_ESTATE) | QUALITY_MUNDANE | 0x00),
                    true,
                    false,
                    0.4,
                    5
            ),
            //todo - more player made armors!
            new Armor(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ancient bronze cuirass")
                            .setDescription("light armor of ancient bronze")
                            .manufactureItemTemplate(DURABILITY_UNIT * 6, MaterialDefinitions.BRONZE,
                                    FAMILY_ARMOR | Item.shiftTheme(DARK_GROVE) | QUALITY_COMMON | 0x00),
                    true,
                    false,
                    0.55,
                    7
            ),
            //todo - more theme drop armors!
            new Text(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("torn notepaper")
                            .setDescription("a piece of paper torn from a notebook")
                            .manufactureItemTemplate(1, MaterialDefinitions.PARCHMENT,
                                    FAMILY_TEXT | Item.shiftTheme(DARK_GROVE) | QUALITY_SCARCE | 0x01),
                    YSIAN,
                    0,
                    LoreDefinitions.DARK_GROVE_ARRIVAL + 1,
                    LoreDefinitions.DARK_GROVE_COMPLETION - 1
            ),
            //todo - more dark grove texts?
            //todo - more texts
    };
    public static Item get(int itemID) {
        int lowerLimit = 0;
        int upperLimit = ORDERED_ITEMS.length - 1;
        int searchIndex = upperLimit / 2;
        int indexedID;
        while(true) {
            indexedID = ORDERED_ITEMS[searchIndex].getID();
            if (indexedID > itemID) {
                upperLimit = searchIndex;
                searchIndex = (searchIndex + lowerLimit) / 2;
            } else if (indexedID < itemID) {
                lowerLimit = searchIndex;
                searchIndex = ((searchIndex + upperLimit) / 2) + 1;
            } else break;
            if (upperLimit == lowerLimit) {
                throw new IllegalStateException("Item with ID " + itemID + "not extant.");
            }
        }
        return ORDERED_ITEMS[searchIndex].clone();
    }

    public static void checkOrder() {
        int thisID;
        int lastID = 0;
        for (Item i : ORDERED_ITEMS) {
            thisID = i.getID();
            if (thisID <= lastID) throw new IllegalStateException("Bad order in ItemDefinitions - Last was " + lastID + " but this was " + thisID);
            lastID = thisID;
        }
    }

    /**
     * Return an item slot carrying an amount of scrap resources corresponding to the provided item.
     */
    public static ItemSlot scrap(DegradableItem degradableItem) {
        Material material = degradableItem.getMaterial();
        int scrapValue = degradableItem.getDurability() / material.getHardness();
        Item scrapResource = null;
        for (AllItemIterator aii = (AllItemIterator)iterator(); aii.hasNext();) {
            scrapResource = aii.next();
            if (scrapResource.getItemFamily() < FAMILY_TRANSIENT_RESOURCE) continue;
            if (scrapResource.getMaterial().equals(material)) break;
        }
        return ItemSlot.make(scrapResource, Session.getRNG().nextInt(scrapValue / DURABILITY_UNIT) + 1);
    }

    public static Iterator<Item> iterator() {
        return new AllItemIterator();
    }
}
