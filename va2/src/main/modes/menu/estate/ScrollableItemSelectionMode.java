package main.modes.menu.estate;

import io.out.GUIManager;
import main.Session;
import main.modes.menu.MenuMode;

public class ScrollableItemSelectionMode extends MenuMode {

    private final ItemSelectionExecutor EXECUTOR;

    public ScrollableItemSelectionMode(ItemSelectionExecutor executor) {
        EXECUTOR = executor;
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        EXECUTOR.select(index);
    }

    @Override
    public void to() {
        //todo - generate a menu from the INVENTORY - was: setMenu(MenuDefinitions.getMainMenu());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from(){
        EXECUTOR.handleSelection();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        //todo - display the selection title, then the inventory starting from the selected index -
        // show items in rarity colors - if menu and highlighted, invert background/foreground
        // was: gm.printMenu(0.35, menu);
    }
}
