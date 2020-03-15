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
    public static final InnateWeapon BARE_HANDED = new InnateWeapon(
            "bare fists",
            "<unarmed>",
            25,
            10,
            .5,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            new WeaponDamage("punch&", 1.0, DamageType.IMPACT)
    );

    public static final InnateWeapon SPIDER_BITE = new InnateWeapon(
            "arachnid fangs",
            "spider bite",
            8,
            3,
            .5,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
    );

    public static final InnateWeapon WOLF_BITE = new InnateWeapon(
            "canine maw",
            "wolf bite",
            15,
            3,
            .33,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
    );

    public static final InnateWeapon BEAR_SWAT = new InnateWeapon(
            "ursine paw",
            "bear swat",
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

    public static final InnateWeapon BEAR_BITE = new InnateWeapon(
            "ursine maw",
            "bear bite",
            22,
            4,
            .33,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            new WeaponDamage("bite$", 1.0, DamageType.PUNCTURE)
    );
    public static final InnateWeapon ACID_STING = new InnateWeapon(
            "barbed chitinous stinger",
            "acidic sting",
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
