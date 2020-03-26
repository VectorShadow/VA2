package combat.melee.weapons;

import combat.DamageType;
import combat.WeaponDamage;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import status.StatusType;
import util.ArrayListBuilder;
import world.WorldObjectTemplateFactory;
import world.item.MeleeWeapon;
import world.item.material.MaterialDefinitions;
import static world.item.ItemQuality.*;

/**
 * Define all weapons for use in the game.
 * Note that weapons which have multiple WeaponDamage values within a continuum should place the most
 * powerful value as the base, since Heavy Blows will resolve to the base rather than rolling a random value.
 */
public class MeleeWeaponDefinitions {
    public static final MeleeWeapon BARE_HANDED =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bare fists")
                            .setDescription("<unarmed>")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    25,
                    10,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("punch&", 1.0, DamageType.IMPACT)
            );

    public static final MeleeWeapon SPIDER_BITE =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("arachnid fangs")
                            .setDescription("spider bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    8,
                    3,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );

    public static final MeleeWeapon WOLF_BITE =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("canine maw")
                            .setDescription("wolf bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    15,
                    3,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );
    public static final MeleeWeapon RABID_WOLF_BITE =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("frothing canine maw")
                            .setDescription("rabid wolf bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
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
            );

    public static final MeleeWeapon BEAR_SWAT =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ursine paw")
                            .setDescription("bear swat")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
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
            );

    public static final MeleeWeapon BEAR_BITE =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("ursine maw")
                            .setDescription("bear bite")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    22,
                    4,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );
    public static final MeleeWeapon ACID_STING =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("barbed chitinous stinger")
                            .setDescription("acidic sting")
                            .setSymbols(',')
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    63,
                    15,
                    .1,
                    new int[] {-25, 25, 10},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("viciously sting$", 1.33, DamageType.ACID),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.8,
                                    new WeaponDamage("sting$", 0.75, DamageType.PUNCTURE))).build())
            );
    public static final MeleeWeapon BRONZE_SHORT_SWORD =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("bronze short sword")
                            .setDescription("a short straight sword made of bronze")
                            .manufactureItemTemplate(20_000, MaterialDefinitions.BRONZE, MUNDANE_PRODUCT),
                    true,
                    false,
                    48,
                    32,
                    .33,
                    new int[] {0, 5, 15},
                    MeleeStyle.ONE_HAND,
                    MeleeWeaponClass.SHORT_BLADES,
                    new Continuum<>(
                            new WeaponDamage("slash&", 1.0, DamageType.REND),
                            ArrayListBuilder.initialize().addElement(new Pair<>(0.33,
                                    new WeaponDamage("stab", 1.0, DamageType.PUNCTURE))).build()
                    )
            );
    public static final MeleeWeapon MOCCASIN_FANG =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("water moccasin fangs")
                            .setDescription("needle sharp fangs of a venomous snake")
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    57,
                    22,
                    .25,
                    new int[]{0, 10, 0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE, StatusType.VENOM_0, 0.4)
                    )
            );
    public static final MeleeWeapon WEB_STRIKE =
            new MeleeWeapon(
                    WorldObjectTemplateFactory
                            .initialize()
                            .setName("heavy silken strand")
                            .setDescription("a sticky strand of heavy silk webbing")
                            .manufactureItemTemplate(1, MaterialDefinitions.PLACEHOLDER, INNATE_ITEM),
                    false,
                    true,
                    96,
                    32,
                    .33,
                    new int[]{10, 0, 0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                            new WeaponDamage("throw$ a silken web at", 1.0, DamageType.IMPACT, StatusType.WEBBED, 0.15)
                    )
            );
}
