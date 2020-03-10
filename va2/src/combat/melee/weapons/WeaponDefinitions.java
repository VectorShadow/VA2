package combat.melee.weapons;

import combat.DamageType;

import java.awt.*;

public class WeaponDefinitions {
    public static final InnateWeapon BARE_HANDED = new InnateWeapon(
            "<no weapon equipped>",
      "<unarmed>",
            Color.WHITE, //todo - Color Standards!
      25,
      10,
      .5,
      new int[] {0,0,0},
      MeleeStyle.DUAL_WEAPON,
      MeleeWeaponClass.FIST_CLAW,
      DamageType.IMPACT
    );
}
