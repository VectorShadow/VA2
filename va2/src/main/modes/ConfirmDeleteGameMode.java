package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import main.Session;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;

public class ConfirmDeleteGameMode implements OperatingMode {
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
        switch (ke.getKeyCode()) {
            case VK_ENTER:
                Session.getFileManager().saveProfile();
                Session.getFileManager().deleteSavedGame();
                do {
                    Session.getModeManager().revert();
                } while (Session.getModeManager().getCurrentMode() instanceof MainGameViewMode);
                Session.reset();
                break;
            case VK_ESCAPE:
                Session.getModeManager().revert();
                break;
        }
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.printCenteredBlock(
                0.45,
                new String[] {
                        "Really quit without saving?",
                        "This will delete your current game, although your profile will persist.",
                        "[Enter to confirm, Escape to cancel]"
                });
    }

    @Override
    public void from() {
        //nothing to do here
    }
}
