package main.modes.menu.estate;

import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class LaboratoryMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getLaboratoryOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.LABORATORY_OPTIONS_RESEARCH_FORMULAE:
                //todo
                return;
            case MenuDefinitions.LABORATORY_OPTIONS_PREPARE_FORMULAE:
                //todo
                return;
            case MenuDefinitions.LABORATORY_OPTIONS_PERFORM_REAGENT_ALCHEMY:
                //todo
                return;
            case MenuDefinitions.LABORATORY_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.LABORATORY_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
