package engine.action;

import combat.melee.weapons.MeleeWeapon;

/**
 * All melee actions implement this for MeleeResolver.
 */
public interface MeleeAttackAction {
    MeleeWeapon getMeleeWeapon();
}
