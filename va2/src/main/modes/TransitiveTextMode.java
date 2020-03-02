package main.modes;

import io.out.DisplayStandards;
import io.out.GUIManager;
import main.Session;
import resources.glyph.image.GlyphString;

import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;

public class TransitiveTextMode implements OperatingMode {

    private final String text;
    private final OperatingMode targetMode;

    public TransitiveTextMode(String text, OperatingMode targetMode){
        this.text = text;
        this.targetMode = targetMode;
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
        Session.getModeManager().revert(); //remove this mode from the stack - we do not need to return to it
        Session.getModeManager().transitionTo(targetMode); //then transition to the target mode
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        gm.printGlyphString(
                4,
                4,
                new GlyphString(text, DisplayStandards.TEXT_DEFAULT_BACKGROUND, DisplayStandards.TEXT_DEFAULT_FOREGROUND)
        );
    }

    @Override
    public void from() {
        //no special cleanup required
    }
}
