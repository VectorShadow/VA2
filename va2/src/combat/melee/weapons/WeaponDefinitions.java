package combat.melee.weapons;

import combat.DamageType;

public class WeaponDefinitions {
    public static final InnateWeapon BARE_HANDED = new InnateWeapon(
      25,
      10,
      .5,
      new int[] {0,0,0},
      MeleeStyle.DUAL_WEAPON,
      MeleeWeaponClass.FIST_CLAW,
      DamageType.IMPACT
    );
}
