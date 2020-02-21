package main.modes;

import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * Primary agent for operating mode transition handling.
 */
public class ModeManager {
    private static Stack<Mode> modeStack = new Stack<>();

    public ModeManager() {
        transitionTo(new BottomMode());
    }
    public void handleKeyPress(KeyEvent ke) {
        modeStack.peek().in(ke);
    }

    public void revert() {
        modeStack.pop().from();
        modeStack.peek().to();
    }
    public void transitionTo(Mode mode) {
        modeStack.push(mode);
        mode.to();
    }
}
