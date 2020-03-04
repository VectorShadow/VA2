package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class ArcheryRangeMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getArcheryRangeOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.ARCHERY_RANGE_OPTIONS_TRAIN:
                //todo
                return;
            case MenuDefinitions.ARCHERY_RANGE_OPTIONS_TECHNIQUES:
                //todo
                return;
            case MenuDefinitions.ARCHERY_RANGE_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.ARCHERY_RANGE_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
