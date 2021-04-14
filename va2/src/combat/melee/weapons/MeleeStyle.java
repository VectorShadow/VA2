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
    SHIELD;

    public String shortDescribe() {
        switch (this) {
            case ONE_HAND: return "1H";
            case TWO_HAND: return "2H";
            case DUAL_WEAPON: return "2X";
            case SHIELD: return "&S";
            default: throw new IllegalArgumentException();
        }
    }
}
