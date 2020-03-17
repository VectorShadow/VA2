package combat.melee.weapons;

import combat.DamageType;
import combat.WeaponDamage;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;
import world.WorldObjectTemplateFactory;
import world.item.InteractiveItem;
import world.item.material.MaterialDefinitions;

/**
 * Define all weapons for use in the game.
 * Note that weapons which have multiple WeaponDamage values within a continuum should place the most
 * powerful value as the base, since Heavy Blows will resolve to the base rather than rolling a random value.
 */
public class WeaponDefinitions {
    public static final ResolvableMeleeWeapon BARE_HANDED =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                        WorldObjectTemplateFactory
                                .initialize()
                                .setName("bare fists")
                                .setDescription("<unarmed>")
                                .setSymbols(',')
                                .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
                    25,
                    10,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("punch&", 1.0, DamageType.IMPACT)
            );

    public static final ResolvableMeleeWeapon SPIDER_BITE =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                            WorldObjectTemplateFactory
                                    .initialize()
                                    .setName("arachnid fangs")
                                    .setDescription("spider bite")
                                    .setSymbols(',')
                                    .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
                    8,
                    3,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );

    public static final ResolvableMeleeWeapon WOLF_BITE =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                            WorldObjectTemplateFactory
                                    .initialize()
                                    .setName("canine maw")
                                    .setDescription("wolf bite")
                                    .setSymbols(',')
                                    .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
                    15,
                    3,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );

    public static final ResolvableMeleeWeapon BEAR_SWAT =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                            WorldObjectTemplateFactory
                                    .initialize()
                                    .setName("ursine paw")
                                    .setDescription("bear swat")
                                    .setSymbols(',')
                                    .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
                    30,
                    12,
                    .45,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new Continuum<>(
                    new WeaponDamage("claw$", 1.25, DamageType.REND),
                    ArrayListBuilder.initialize().addElement(new Pair<>(0.66,
                            new WeaponDamage("swat$", 0.9, DamageType.IMPACT))).build())
            );

    public static final ResolvableMeleeWeapon BEAR_BITE =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                            WorldObjectTemplateFactory
                                    .initialize()
                                    .setName("ursine maw")
                                    .setDescription("bear bite")
                                    .setSymbols(',')
                                    .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
                    22,
                    4,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            );
    public static final ResolvableMeleeWeapon ACID_STING =
            new ResolvableMeleeWeapon(
                    new InteractiveItem(
                            WorldObjectTemplateFactory
                                    .initialize()
                                    .setName("barbed chitinous stinger")
                                    .setDescription("acidic sting")
                                    .setSymbols(',')
                                    .manufactureItemTemplate(1),
                            MaterialDefinitions.PLACEHOLDER
                    ),
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
}
