package combat.melee.weapons;

/**
 * A differentiation of weapons into specific types. These are grouped by common characteristics, and
 * special abilities for forms should take these into account - for example, a special attack from a blunted form
 * might have an additional chance to stun.
 *
 * Some damage types within classes may vary - for example, pikes and scythes are both polearms, but a pike can
 * only pierce, and a scythe can only rend. Others, like a halberd, may have a primary and secondary type.
 * This should be accounted for in autoattacks and special abilities.
 */
public enum MeleeWeaponClass {
    FIST_CLAW,
    SHORT_BLADES,
    SWORDS,
    BLUNTED,
    CHAIN,
    POLE,
    AXE;

    public String shortDescribe() {
        switch (this) {
            case FIST_CLAW: return "Fist/Claw";
            case SHORT_BLADES: return "Short Blade";
            case SWORDS: return "Sword";
            case BLUNTED: return "Blunt";
            case CHAIN: return "Chain";
            case POLE: return "Pole";
            case AXE: return "Axe";
            default: throw new IllegalArgumentException();
        }
    }
}
