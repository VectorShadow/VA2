package main.modes.menu;

import io.out.GUIManager;
import main.Session;
import main.modes.ScrollableTextMode;
import world.lore.LoreDefinitions;

/**
 * Menu for accessing in-game Lore. Default constructor is the top level, which is used to access sub-levels.
 * Parametrized constructor is for sub-levels, which then access individual Lore screens.
 */
public class LoreMenuMode extends MenuMode {

    private static final int TOP_LEVEL = -1;

    final int THEME;

    public LoreMenuMode(){
        this(TOP_LEVEL);
    }

    public LoreMenuMode(int t) {
        THEME = t;
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        final int LAST_INDEX = menu.size() - 1;
        if (index == LAST_INDEX) { //exit option
            Session.getModeManager().revert();
        } else if (THEME == TOP_LEVEL) { //top level menu
            Session.getModeManager().transitionTo(new LoreMenuMode(index));
        } else { //theme menus
            Session.getModeManager().transitionTo( new ScrollableTextMode(LoreDefinitions.loreAt(THEME, index)));
        }
    }

    @Override
    public void to() {
        setMenu(MenuDefinitions.getLoreMenu(THEME));
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //nothing to do
    }
}
