package combat.armor;

import world.WorldObjectTemplateFactory;
import world.item.Armor;
import world.item.loadout.EquipmentSlot;
import world.item.material.MaterialDefinitions;
import static world.item.ItemQuality.*;

public class ArmorDefinitions {
    public static final Armor UNARMORED = new Armor(
            WorldObjectTemplateFactory.initialize().setName("<no armor>").setDescription("<no armor>").manufactureItemTemplate(1, INNATE_ITEM),
            false,
            MaterialDefinitions.PLACEHOLDER,
            true,
            0.0,
            0
    );
    public static final Armor LEATHER_VEST = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("leather vest")
                    .setDescription("a protective vest of supple leather")
                    .manufactureItemTemplate(5_000, MUNDANE_PRODUCT),
            true,
            MaterialDefinitions.SOFT_LEATHER,
            false,
            0.4,
            5
    );
    public static final Armor ARACHNID_EXOSKELETON = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("arachnid exoskeleton")
                    .setDescription("the natural armor of a spider")
                    .manufactureItemTemplate(1, INNATE_ITEM),
            false,
            MaterialDefinitions.CHITIN,
            true,
            0.92,
            3
    );
    public static final Armor ANCIENT_ARACHNID_EXOSKELETON = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("ancient arachnid exoskeleton")
                    .setDescription("the natural armor of an ancient spider")
                    .manufactureItemTemplate(1, INNATE_ITEM),
            false,
            MaterialDefinitions.ANCIENT_CHITIN,
            true,
            0.92,
            8
    );
    public static final Armor SNAKESKIN_SCALE_ARMOR = new Armor(
            WorldObjectTemplateFactory
                    .initialize()
                    .setName("scale covered snakeskin")
                    .setDescription("the natural armor of a serpent")
                    .manufactureItemTemplate(1, INNATE_ITEM),
            false,
            MaterialDefinitions.SNAKESKIN,
            true,
            1,
            5
    );
}
