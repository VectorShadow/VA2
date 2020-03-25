package main.modes;

import io.in.InputCommandList;
import main.Session;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * This interface specifies the behavior for the mode the program is operating in.
 */
public interface OperatingMode {
    /**
     * Access the InputCommandList for this Mode.
     */
    InputCommandList getInput();
    /**
     * Handle transition to this mode.
     */
    void to();

    /**
     * Handle the specified key event while in this mode.
     * @param ke
     */
    void in(KeyEvent ke);

    /**
     * Draw the screen while in this mode. Note that this method should be used to update the GUI fields used
     * to draw the screen - the redraw itself is handled by the GUIManager's ScreenRefresherDaemon.
     */
    void out();

    /**
     * Handle reversion from this mode.
     */
    void from();

    static boolean overrideHandleInput(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case VK_ENTER:
                if (ke.getModifiersEx() == ALT_DOWN_MASK) {
                    Session.getGuiManager().toggleFullScreenMode();
                    return true;
                }
                break;
            //todo - other override cases?
        }
        return false;
    }
}
