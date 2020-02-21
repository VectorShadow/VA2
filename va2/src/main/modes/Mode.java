package main.modes;

import java.awt.event.KeyEvent;

/**
 * This interface specifies the behavior for the mode the program is operating in.
 */
public interface Mode {
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
}
