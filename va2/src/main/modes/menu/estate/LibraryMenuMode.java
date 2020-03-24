package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.LoreMenuMode;
import main.modes.menu.MenuDefinitions;

public class LibraryMenuMode extends EstateRoomMenuMode {

    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getLibraryOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.LIBRARY_OPTIONS_TEXTS:
                //todo
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_LORE:
                Session.getModeManager().transitionTo(new LoreMenuMode());
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_LANGUAGES:
                //todo
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
