package main.modes.menu;

import io.out.GUIManager;
import main.Session;

public class ProfileMenuMode extends MenuMode {
    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.PROFILE_MENU_KNOWN_LANGUAGES:
                //todo
                return;
            case MenuDefinitions.PROFILE_MENU_KNOWN_RECIPES:
                //todo
                return;
            case MenuDefinitions.PROFILE_MENU_KILL_COUNT_AND_ENEMY_INFO:
                //todo
                return;
            case MenuDefinitions.PROFILE_MENU_FULL_SCORES:
                //todo
                return;
            case MenuDefinitions.PROFILE_MENU_RESOURCES_AND_UPGRADES:
                //todo
                return;
            case MenuDefinitions.PROFILE_MENU_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }

    }

    @Override
    public void to() {
        setMenu(MenuDefinitions.getProfileMenu());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //nothing to do
    }
}
