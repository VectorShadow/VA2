package world.item.loadout;

public class WeaponsBelt {
    private Equippable wieldedWeapon = null;
    private Equippable[] beltedWeapons;

    public WeaponsBelt(int capacity) {
        beltedWeapons = new Equippable[capacity];
    }

    public void switchTo(int index) {
        Equippable e = beltedWeapons[index];
        beltedWeapons[index] = wieldedWeapon;
        wieldedWeapon = e;
    }
    public boolean wield(Equippable e) {
        if (wieldedWeapon == null && e.getEquipmentSlot() == Equippable.EquipmentSlot.WIELDED) {
            wieldedWeapon = e;
            return true;
        }
        return false;
    }
}
