package engine.action;

import combat.melee.weapons.ResolvableMeleeWeapon;

/**
 * All melee actions implement this for MeleeResolver.
 */
public interface MeleeAttackAction {
    ResolvableMeleeWeapon getResolvableMeleeWeapon();
}
