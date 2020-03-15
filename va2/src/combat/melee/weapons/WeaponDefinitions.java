package combat.melee.weapons;

import combat.DamageType;
import combat.WeaponDamage;
import resources.continuum.Continuum;
import resources.continuum.Pair;
import util.ArrayListBuilder;

/**
 * Define all weapons for use in the game.
 * Note that weapons which have multiple WeaponDamage values within a continuum should place the most
 * powerful value as the base, since Heavy Blows will resolve to the base rather than rolling a random value.
 */
public class WeaponDefinitions {
    public static final InnateMeleeWeapon BARE_HANDED = new InnateMeleeWeapon(
            "bare fists",
            "<unarmed>",
            new ResolvableMeleeWeapon(
                    25,
                    10,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("punch&", 1.0, DamageType.IMPACT)
            )
    );

    public static final InnateMeleeWeapon SPIDER_BITE = new InnateMeleeWeapon(
            "arachnid fangs",
            "spider bite",
            new ResolvableMeleeWeapon(
                    8,
                    3,
                    .5,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            )
    );

    public static final InnateMeleeWeapon WOLF_BITE = new InnateMeleeWeapon(
            "canine maw",
            "wolf bite",
            new ResolvableMeleeWeapon(
                    15,
                    3,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            )
    );

    public static final InnateMeleeWeapon BEAR_SWAT = new InnateMeleeWeapon(
            "ursine paw",
            "bear swat",
            new ResolvableMeleeWeapon(
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
            )
    );

    public static final InnateMeleeWeapon BEAR_BITE = new InnateMeleeWeapon(
            "ursine maw",
            "bear bite",
            new ResolvableMeleeWeapon(
                    22,
                    4,
                    .33,
                    new int[] {0,0,0},
                    MeleeStyle.DUAL_WEAPON,
                    MeleeWeaponClass.FIST_CLAW,
                    new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
            )
    );
    public static final InnateMeleeWeapon ACID_STING = new InnateMeleeWeapon(
            "barbed chitinous stinger",
            "acidic sting",
            new ResolvableMeleeWeapon(
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
            )
    );
}
