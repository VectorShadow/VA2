package world.item.loadout;

import combat.armor.ArmorDefinitions;
import combat.melee.weapons.MeleeWeaponDefinitions;
import main.extensible.Saveable;

/**
 * The class responsible for maintaining the player's equipped and carried items.
 */
public class Equipment extends Saveable {
    //todo - this should consist of a series of submodules, which each contain various portions of the
    // player's loadout. If the player unlocks and builds an upgrade, it should be automatically swapped in,
    // and any items tracked by it should be transferred over.
    // For example, if a player has a basic reagent pouch capable of a grimoire and three individual reagents,
    // and then upgrades to a more advanced pouch with space for four reagents, any grimoire and reagents currently
    // equipped in the pouch should be loaded into the new one, then the appropriate slot here loaded with that.
    private static final int WEAPON_BELT_MODULE = 0;
    private static final int BODY_ARMOR_MODULE = 1;

    private static final int LOADOUT_MODULE_COUNT = 2; //todo - keep updated!

    LoadoutModule[] loadoutModules = new LoadoutModule[LOADOUT_MODULE_COUNT];

    public WeaponsBelt getWeaponsBelt() {
        return (WeaponsBelt)loadoutModules[WEAPON_BELT_MODULE];
    }
    public BodyArmor getBodyArmor() {
        return (BodyArmor)loadoutModules[BODY_ARMOR_MODULE];
    }
    public void setLoadoutModule(int index, LoadoutModule lm) {
        loadoutModules[index] = lm;
    }
    public void setStartingItems() {
        WeaponsBelt weaponsBelt = new WeaponsBelt(0);
        weaponsBelt.wield(MeleeWeaponDefinitions.BRONZE_SHORT_SWORD.clone());
        setLoadoutModule(WEAPON_BELT_MODULE, weaponsBelt);
        BodyArmor bodyArmor = new BodyArmor();
        bodyArmor.wear(ArmorDefinitions.LEATHER_VEST.clone());
        setLoadoutModule(BODY_ARMOR_MODULE, bodyArmor);
        //todo - add more items!
    }
}
