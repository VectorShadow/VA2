package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class MausoleumMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getMausoleumOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.MAUSOLEUM_OPTIONS_HIGH_SCORES:
                //todo
                return;
            case MenuDefinitions.MAUSOLEUM_OPTIONS_CHALLENGE_MODES:
                //todo
                return;
            case MenuDefinitions.MAUSOLEUM_OPTIONS_DUNGEON_COMPLETION:
                //todo
                return;
            case MenuDefinitions.MAUSOLEUM_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
