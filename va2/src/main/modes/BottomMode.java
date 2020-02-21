package main.modes;

import contract.menu.Menu;
import contract.menu.MenuBuilder;
import contract.menu.MenuOption;
import io.out.GUIManager;
import main.Meta;
import main.Session;
import menu.MenuDefinitions;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The startup/shutdown operating mode.
 */
public class BottomMode implements Mode {

    private boolean isNewSession = true;

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        gm.clearScreen();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (isNewSession) {
            //todo - check the file system for a player profile. if extant, go directly to the main menu.
            // otherwise, switch to a special mode which will prompt the player for unique information and create
            // the profile. This mode should then switch to the main menu.
            Session.getModeManager().transitionTo(new MainMenuMode(MenuDefinitions.getMainMenu()));
            isNewSession = false;
        } else {
            Session.getModeManager().revert();
        }
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        if (isNewSession) {
            gm.printCenteredBlock(0.2, new String[] {
                    Meta.gameTitle(),
                    "by " + Meta.studio(),
                    "Version: " + Meta.version()
            }, Color.BLACK, Color.GREEN);
            gm.printCenteredLine(0.85, "[Press any key to continue.]", Color.BLACK, Color.GREEN);
        } else {
            gm.printCenteredLine(0.45, "Thanks for playing ACE. Press any key to quit.", Color.BLACK, Color.GREEN);
        }
    }

    @Override
    public void from() {
        System.exit(0);
    }
}
