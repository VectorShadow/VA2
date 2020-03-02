package main.modes;

import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * Primary agent for operating mode transition handling.
 */
public class ModeManager {
    private static Stack<OperatingMode> operatingModeStack = new Stack<>();

    public ModeManager() {
        transitionTo(new BottomMode());
    }

    public OperatingMode getCurrentMode() {
        return operatingModeStack.peek();
    }
    public void handleKeyPress(KeyEvent ke) {
        operatingModeStack.peek().in(ke);
    }

    public void revert() {
        operatingModeStack.pop().from();
        operatingModeStack.peek().to();
    }
    public void transitionTo(OperatingMode operatingMode) {
        operatingModeStack.push(operatingMode);
        operatingMode.to();
    }
}
