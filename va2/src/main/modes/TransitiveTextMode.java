package main.modes;

import main.Session;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class TransitiveTextMode extends TextMode {

    private final OperatingMode targetMode;

    public TransitiveTextMode(String text, OperatingMode targetMode){
        super(text);
        this.targetMode = targetMode;
    }

    public void in(KeyEvent e) {
        super.in(e);
        if (e.getKeyCode() == VK_ENTER)
            Session.getModeManager().transitionTo(targetMode); //transition to the target mode
    }
}
