package main.modes;

import io.out.GUIManager;
import main.MetaData;
import main.Session;
import menu.MenuDefinitions;

import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * The startup/shutdown operating mode.
 */
public class BottomMode implements OperatingMode {

    private boolean isNewSession = true;

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
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
        gm.clearScreen();
        if (isNewSession) {
            gm.printCenteredBlock(0.2, new String[] {
                    MetaData.gameTitle(),
                    "by " + MetaData.studio(),
                    "Version: " + MetaData.version()
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
