package engine.action;

import world.item.MeleeWeapon;

/**
 * All melee actions implement this for MeleeResolver.
 */
public interface MeleeAttackAction {
    MeleeWeapon getMeleeWeapon();
}
