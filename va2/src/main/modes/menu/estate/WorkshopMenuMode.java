package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class WorkshopMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getWorkshopOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.WORKSHOP_OPTIONS_REPAIR:
                //todo
                return;
            case MenuDefinitions.WORKSHOP_OPTIONS_CONSTRUCT:
                //todo
                return;
            case MenuDefinitions.WORKSHOP_OPTIONS_COAT:
                //todo
                return;
            case MenuDefinitions.WORKSHOP_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.WORKSHOP_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
