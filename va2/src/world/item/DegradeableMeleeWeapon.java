package world.item;

import combat.melee.weapons.WieldedMeleeWeapon;
import combat.melee.weapons.ResolvableMeleeWeapon;

public class DegradeableMeleeWeapon extends DegradeableItem implements WieldedMeleeWeapon {

    private final ResolvableMeleeWeapon RESOLVABLE_MELEE_WEAPON;

    public DegradeableMeleeWeapon(ItemTemplate it, ResolvableMeleeWeapon rmw) {
        super(it);
        RESOLVABLE_MELEE_WEAPON = rmw;
    }

    @Override
    public ResolvableMeleeWeapon getResolvableMeleeWeapon() {
        return RESOLVABLE_MELEE_WEAPON;
    }
}
