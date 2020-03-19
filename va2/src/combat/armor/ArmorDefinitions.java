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
            EquipmentSlot.WORN,
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
            EquipmentSlot.WORN,
            false,
            0.4,
            5
    );
}
