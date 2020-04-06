package main.modes.menu.estate.armory;

import io.out.GUIManager;
import main.Session;
import main.modes.menu.MenuDefinitions;
import main.modes.menu.MenuMode;
import world.item.Armor;
import world.item.EquipableItem;
import world.item.MeleeWeapon;
import world.item.inventory.Inventory;
import world.item.loadout.Equipment;

//todo - this and other inventory based menus might need to scroll! They should also display in rarity colors, and multiple lines per option if possible.
public class EquipMenuMode extends MenuMode {
    @Override
    protected void handleMenuOptionIndex(int index) {
        Inventory armoryEquipment = Session.getPlayer().getArmoryEquipment();
        EquipableItem equipableItem;
        Equipment loadoutEquipment = Session.getPlayer().getEquipment();
        Session.getModeManager().revert();
        if (index != armoryEquipment.size()) {
            equipableItem = (EquipableItem)armoryEquipment.remove(index);
            EquipableItem removedEquipment = (
                    equipableItem instanceof MeleeWeapon
                            ? loadoutEquipment.getWeaponsBelt().wield((MeleeWeapon)equipableItem)
                            : equipableItem instanceof Armor
                            ? loadoutEquipment.getBodyArmor().wear((Armor)equipableItem)
                            : null // placeholder - we should be able to definitively handle all cases
            );
            if (removedEquipment != null)
                armoryEquipment.add(removedEquipment);
        }
    }

    @Override
    public void to() {
        setMenu(MenuDefinitions.getArmoryEquipOptions());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //todo - nothing
    }
}
