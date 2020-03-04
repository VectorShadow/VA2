package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class ArmoryMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getArmoryOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.ARMORY_OPTIONS_EQUIP:
                //todo
                return;
            case MenuDefinitions.ARMORY_OPTIONS_PACK:
                //todo
                return;
            case MenuDefinitions.ARMORY_OPTIONS_SCRAP:
                //todo
                return;
            case MenuDefinitions.ARMORY_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.ARMORY_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
