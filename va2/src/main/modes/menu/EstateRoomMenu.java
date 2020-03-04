package main.modes.menu;

import io.out.GUIManager;
import main.Session;

public class EstateRoomMenu extends MenuMode {
    @Override
    protected void handleMenuOptionIndex(int index) {
        setMenu(MenuDefinitions.getMainMenu());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void to() {

    }

    @Override
    public void from() {

    }
}
