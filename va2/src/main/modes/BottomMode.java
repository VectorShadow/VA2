package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import main.MetaData;
import main.Session;
import main.modes.menu.MainMenuMode;
import world.lore.LoreDefinitions;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * The startup/shutdown operating mode.
 */
public class BottomMode implements OperatingMode {

    private boolean isNewSession = true;

    @Override
    public InputCommandList getInput() {
        return null;
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
        OperatingMode targetMode = new MainMenuMode();
        if (isNewSession) {
            if (Session.getFileManager().loadProfile()) {
                Session.getModeManager().transitionTo(targetMode);
            } else {
                Session.unlockLore(LoreDefinitions.THEME_GENERAL, LoreDefinitions.GENERAL_NEW_PLAYER, targetMode);
            }
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
                    "Version: " + MetaData.version(),
                    "(" + MetaData.lastUpdated() + ")"
            });
            gm.printCenteredLine(0.75, "[Press any key to continue.]");
        } else {
            gm.printCenteredLine(0.45, "Thanks for playing Chronicles of the Abyss.");
        }
    }

    @Override
    public void from() {
        System.exit(0);
    }
}
