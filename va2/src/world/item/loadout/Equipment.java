package world.item.loadout;

import combat.melee.weapons.WeaponDefinitions;

/**
 * The class responsible for maintaining the player's equipped and carried items.
 */
public class Equipment {
    //todo - this should consist of a series of submodules, which each contain various portions of the
    // player's loadout. If the player unlocks and builds an upgrade, it should be automatically swapped in,
    // and any items tracked by it should be transferred over.
    // For example, if a player has a basic reagent pouch capable of a grimoire and three individual reagents,
    // and then upgrades to a more advanced pouch with space for four reagents, any grimoire and reagents currently
    // equipped in the pouch should be loaded into the new one, then the appropriate slot here loaded with that.
    WeaponsBelt weaponsBelt;

    public Equipment() {
        weaponsBelt = new WeaponsBelt(0);
        //weaponsBelt.wield(WeaponDefinitions.BRONZE_SHORT_SWORD);
    }
}
