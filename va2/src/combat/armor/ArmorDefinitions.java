package combat.armor;

import world.WorldObjectTemplateFactory;
import world.item.Armor;
import world.item.loadout.EquipmentSlot;
import world.item.material.MaterialDefinitions;

public class ArmorDefinitions {
    public static final Armor UNARMORED = new Armor(
            WorldObjectTemplateFactory.initialize().setName("<no armor>").setDescription("<no armor>").manufactureItemTemplate(1),
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
                    .manufactureItemTemplate(25_000),
            true,
            MaterialDefinitions.SOFT_LEATHER,
            EquipmentSlot.WORN,
            true,
            0.33,
            5
    );
}
