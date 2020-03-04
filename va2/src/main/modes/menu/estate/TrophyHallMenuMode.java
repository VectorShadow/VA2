package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class TrophyHallMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getTrophyHallOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.TROPHY_HALL_OPTIONS_AUTOPSY:
                //todo
                return;
            case MenuDefinitions.TROPHY_HALL_OPTIONS_INSPECT_TROPHY:
                //todo
                return;
            case MenuDefinitions.TROPHY_HALL_OPTIONS_KILL_RECORDS:
                //todo
                return;
            case MenuDefinitions.TROPHY_HALL_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.TROPHY_HALL_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
