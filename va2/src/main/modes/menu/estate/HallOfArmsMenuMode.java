package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class HallOfArmsMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getHallOfArmsOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.HALL_OF_ARMS_OPTIONS_TRAIN:
                //todo
                return;
            case MenuDefinitions.HALL_OF_ARMS_OPTIONS_FORMS:
                //todo
                return;
            case MenuDefinitions.HALL_OF_ARMS_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.HALL_OF_ARMS_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
