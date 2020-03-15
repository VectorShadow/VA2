package combat.melee.weapons;

import main.extensible.TextDisplayable;

/**
 * This class specifies a WieldedMeleeWeapon that is not derived from an item.
 * It must still be displayable as text, but need not have a Glyph.
 */
public class InnateMeleeWeapon extends TextDisplayable implements WieldedMeleeWeapon {

    private final ResolvableMeleeWeapon RESOLVABLE_MELEE_WEAPON;

    public InnateMeleeWeapon(
            String d,
            String n,
            ResolvableMeleeWeapon rmw
    ) {
        super(d, n, rmw.getColorContinuum());
        RESOLVABLE_MELEE_WEAPON = rmw;
    }

    @Override
    public ResolvableMeleeWeapon getResolvableMeleeWeapon() {
        return RESOLVABLE_MELEE_WEAPON;
    }
}
