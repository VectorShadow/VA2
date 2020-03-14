package combat.melee.weapons;

import combat.DamageType;

public class WeaponDefinitions {
    public static final InnateWeapon BARE_HANDED = new InnateWeapon(
            "punch$",
      "<unarmed>",
      25,
      10,
      .5,
      new int[] {0,0,0},
      MeleeStyle.DUAL_WEAPON,
      MeleeWeaponClass.FIST_CLAW,
      DamageType.IMPACT
    );

    public static final InnateWeapon SPIDER_BITE = new InnateWeapon(
            "bite&",
            "spider bite",
            8,
            3,
            .5,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            DamageType.PUNCTURE
    );

    public static final InnateWeapon WOLF_BITE = new InnateWeapon(
            "bite&",
            "wolf bite",
            15,
            3,
            .33,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            DamageType.PUNCTURE
    );

    public static final InnateWeapon BEAR_SWAT = new InnateWeapon(
            "swat&",
            "bear swat",
            30,
            12,
            .45,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            DamageType.IMPACT
    );

    public static final InnateWeapon BEAR_BITE = new InnateWeapon(
            "bite&",
            "bear bite",
            22,
            4,
            .33,
            new int[] {0,0,0},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            DamageType.PUNCTURE
    );
    public static final InnateWeapon ACID_STING = new InnateWeapon(
            "sting&",
            "acidic sting",
            63,
            15,
            .1,
            new int[] {-25, 25, 10},
            MeleeStyle.DUAL_WEAPON,
            MeleeWeaponClass.FIST_CLAW,
            DamageType.ACID
    );
}
