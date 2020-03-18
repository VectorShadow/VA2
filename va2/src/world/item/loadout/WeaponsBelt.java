package world.item.loadout;

import combat.melee.weapons.MeleeWeaponDefinitions;
import main.Session;
import world.item.MeleeWeapon;

public class WeaponsBelt extends LoadoutModule {
    private MeleeWeapon wieldedWeapon = null;
    private MeleeWeapon[] beltedWeapons;

    public WeaponsBelt(int capacity) {
        beltedWeapons = new MeleeWeapon[capacity];
    }

    public void switchTo(int index) {
        MeleeWeapon mw = beltedWeapons[index];
        beltedWeapons[index] = wieldedWeapon;
        wieldedWeapon = mw;
    }
    public MeleeWeapon wield(MeleeWeapon mw) {
        MeleeWeapon oldMeleeWeapon = wieldedWeapon;
        wieldedWeapon = mw;
        Session.getPlayer().getActor().getCombatant().setMeleeWeapons(
                wieldedWeapon == null
                        ? MeleeWeaponDefinitions.BARE_HANDED
                        : wieldedWeapon
        );
        return oldMeleeWeapon;
    }
}
