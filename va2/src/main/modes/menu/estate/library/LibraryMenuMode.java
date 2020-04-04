package main.modes.menu.estate.library;

import main.Session;
import main.modes.ScrollingTextMode;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.LoreMenuMode;
import main.modes.menu.MenuDefinitions;
import main.modes.menu.estate.UpgradeMenuMode;
import main.progression.estate.EstateProgression;

public class LibraryMenuMode extends EstateRoomMenuMode {

    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getLibraryOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.LIBRARY_OPTIONS_TEXTS:
                Session.getModeManager().transitionTo(new ResearchTextsMenuMode());
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_LORE:
                Session.getModeManager().transitionTo(new LoreMenuMode());
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_LANGUAGES:
                Session.getModeManager().transitionTo(new ScrollingTextMode(Session.getLanguageKnowledge().toString()));
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_UPGRADE:
                Session.getModeManager().transitionTo(new UpgradeMenuMode(MenuDefinitions.getUpgradeOptions(EstateProgression.INDEX_LIBRARY)));
                return;
            case MenuDefinitions.LIBRARY_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
