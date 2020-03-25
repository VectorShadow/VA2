package io.in;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class InputInfo extends InputCommand {
    private final String INPUT_DESCRIPTION;

    public InputInfo(String desc, String name) {
        super(CHAR_UNDEFINED, name);
        INPUT_DESCRIPTION = desc;
    }
    @Override
    public boolean test(KeyEvent ke) {
        return false;
    }

    public void overrideInput(KeyEvent ke) {
        //do nothing
    }
    public void repossessInput(KeyEvent ke) {
        //do nothing
    }
    @Override
    public String toString() {
        return INPUT_DESCRIPTION;
    }
}
