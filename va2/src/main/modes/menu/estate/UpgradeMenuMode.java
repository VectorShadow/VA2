package main.modes.menu.estate;

import contract.menu.Menu;
import io.out.GUIManager;
import main.Session;
import main.modes.menu.MenuDefinitions;
import main.modes.menu.MenuMode;

public class UpgradeMenuMode extends MenuMode {

    public UpgradeMenuMode(Menu menu) {
        setMenu(menu);
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        String s = menu.getOptionName(index);
        if (s.equals(MenuDefinitions.CANCEL)) {
            Session.getModeManager().revert();
        } else {
            Session.getEstateProgression().get(s).unlock(Session.getLegacyResources());
            Session.getModeManager().revert();
        }
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //nothing to do
    }
}
