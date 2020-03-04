package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class RitualChamberMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getRitualChamberOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.RITUAL_CHAMBER_OPTIONS_PERFORM_RITUAL:
                //todo
                return;
            case MenuDefinitions.RITUAL_CHAMBER_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.RITUAL_CHAMBER_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
