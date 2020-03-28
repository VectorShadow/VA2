package combat.armor;

import world.WorldObjectTemplateFactory;
import world.item.Armor;
import world.item.material.MaterialDefinitions;
import static world.item.ItemQuality.*;

public class ArmorDefinitions {
    public static final Armor UNARMORED = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("<no armor>")
                    .setDescription("<no armor>")
                    .manufactureItemTemplate(1, MaterialDefinitions.FLESH, INNATE_ITEM),
            false,
            true,
            0.0,
            0
    );
    public static final Armor LEATHER_VEST = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("leather vest")
                    .setDescription("a protective vest of supple leather")
                    .manufactureItemTemplate(8_000, MaterialDefinitions.SOFT_LEATHER, MUNDANE_PRODUCT),
            true,
            false,
            0.4,
            5
    );
    public static final Armor ARACHNID_EXOSKELETON = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("arachnid exoskeleton")
                    .setDescription("the natural armor of a spider")
                    .manufactureItemTemplate(1, MaterialDefinitions.CHITIN, INNATE_ITEM),
            false,
            true,
            0.92,
            3
    );
    public static final Armor ANCIENT_ARACHNID_EXOSKELETON = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("ancient arachnid exoskeleton")
                    .setDescription("the natural armor of an ancient spider")
                    .manufactureItemTemplate(1, MaterialDefinitions.ANCIENT_CHITIN, INNATE_ITEM),
            false,
            true,
            0.92,
            8
    );
    public static final Armor SNAKESKIN_SCALE_ARMOR = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("scale covered snakeskin")
                    .setDescription("the natural armor of a serpent")
                    .manufactureItemTemplate(1, MaterialDefinitions.SNAKESKIN, INNATE_ITEM),
            false,
            true,
            1,
            5
    );
    public static final Armor SPIDERSILK_COCOON = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("heavy spidersilk cocoon")
                    .setDescription("a mummy-like wrapping of thick spider silk")
                    .manufactureItemTemplate(1, MaterialDefinitions.SPIDER_SILK, INNATE_ITEM),
            false,
            true,
            0.95,
            10
    );
    public static final Armor WOLF_HIDE = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("wolf hide and fur")
                    .setDescription("wolf skin covered in thick fur")
                    .manufactureItemTemplate(1, MaterialDefinitions.BEAST_HIDE_AND_FUR, INNATE_ITEM),
            false,
            true,
            0.8,
            6
    );
    public static final Armor BEAR_HIDE = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("bear hide and fur")
                    .setDescription("bear fat and skin covered in thick fur")
                    .manufactureItemTemplate(1, MaterialDefinitions.BEAST_HIDE_AND_FUR, INNATE_ITEM),
            false,
            true,
            0.8,
            9
    );
}
