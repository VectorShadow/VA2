package io.in;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputCommand {
    private final int DEFAULT_KEYCODE;
    private int overrideKeycode;
    private final int DEFAULT_KEY_MODIFIERS;
    private int overrideKeyModifiers;
    private final String DEFAULT_NAME;
    private String overrideName;

    public InputCommand(int keyCode, String name) {
        this(keyCode, CHAR_UNDEFINED, name);
    }

    public InputCommand(int keyCode, int keyMask, String name) {
        overrideKeycode = DEFAULT_KEYCODE = keyCode;
        overrideKeyModifiers = DEFAULT_KEY_MODIFIERS = keyMask;
        overrideName = DEFAULT_NAME = name;
    }

    public boolean test(KeyEvent ke) {
        return ke.getKeyCode() == overrideKeycode
                && (overrideKeyModifiers == ke.getModifiersEx() || overrideKeyModifiers == CHAR_UNDEFINED);
    }
    public String getName() {
        return overrideName;
    }

    public void overrideInput(KeyEvent ke) {
        overrideKeycode = ke.getKeyCode();
        overrideKeyModifiers = ke.getModifiers();
    }
    public void repossessInput(KeyEvent ke) {
        if (ke.getKeyCode() == overrideKeycode && ke.getModifiersEx() == overrideKeyModifiers) {
            if (ke.getKeyCode() == DEFAULT_KEYCODE && ke.getModifiersEx() == DEFAULT_KEY_MODIFIERS) {
                overrideKeycode = CHAR_UNDEFINED;
                overrideKeyModifiers = CHAR_UNDEFINED;
            } else {
                overrideKeycode = DEFAULT_KEYCODE;
                overrideKeyModifiers = DEFAULT_KEY_MODIFIERS;
            }
        }
    }
    @Override
    public String toString() {
        String s = "";
        switch (overrideKeyModifiers) {
            case SHIFT_DOWN_MASK:
                s += "<shift>";
                break;
            case CTRL_DOWN_MASK:
                s += "<ctrl>";
                break;
            case ALT_DOWN_MASK:
                s += "<alt>";
                break;
        }
        String kc = KeyCodeInterpreter.toString(overrideKeycode);
        if (kc == "")
            throw new IllegalStateException("Unmapped keycode: " + overrideKeycode);
        s += kc;
        return s;
    }
}
