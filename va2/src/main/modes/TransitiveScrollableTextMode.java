package main.modes;

import main.Session;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * A ScrollableTextMode which does not remain on the ModeStack.
 */
public class TransitiveScrollableTextMode extends ScrollableTextMode {

    private final OperatingMode targetMode;

    public TransitiveScrollableTextMode(String text, OperatingMode targetMode){
        super(text);
        this.targetMode = targetMode;
    }

    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(this, ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
        super.in(ke);
        if (ke.getKeyCode() == VK_ENTER)
            Session.getModeManager().transitionTo(targetMode); //transition to the target mode
    }
}
