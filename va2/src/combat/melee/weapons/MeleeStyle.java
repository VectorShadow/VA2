package combat.melee.weapons;

/**
 * The general approach to wielding a weapon. Special abilities for forms should take these into account -
 * for example, one handed weapons might offer more accurate specials, since the weilder can be more precise,
 * or dual wielded weapons might reduce a defender's chance to deflect by overwhelming their capability to do so.
 * Two hand weapons might have a bonus to penetrating armor or overcoming shield blocks.
 */
public enum MeleeStyle {
    ONE_HAND,
    TWO_HAND,
    DUAL_WEAPON,
    SHIELD
}
