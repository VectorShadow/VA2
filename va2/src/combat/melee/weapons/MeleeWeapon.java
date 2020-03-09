package combat.melee.weapons;

import combat.DamageType;

/**
 * All weapons must implement this interface for use by a CombatResolver.
 */
public interface MeleeWeapon {
    int rollRawDamage(int wielderStrength);
    int adjustAccuracy();
    int adjustPrecision();
    int adjustStrength();
    MeleeStyle getMeleeStyle();
    MeleeWeaponClass getMeleeWeaponClass();
    DamageType resolveDamageType();
    MeleeWeapon clone();
}
