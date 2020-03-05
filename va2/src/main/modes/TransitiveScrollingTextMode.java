package main.modes;

import main.Session;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * A ScrollingTextMode which does not remain on the ModeStack.
 */
public class TransitiveScrollingTextMode extends ScrollingTextMode {

    private final OperatingMode targetMode;

    public TransitiveScrollingTextMode(String text, OperatingMode targetMode){
        super(text);
        this.targetMode = targetMode;
    }

    public void in(KeyEvent e) {
        super.in(e);
        if (e.getKeyCode() == VK_ENTER)
            Session.getModeManager().transitionTo(targetMode); //transition to the target mode
    }
}
