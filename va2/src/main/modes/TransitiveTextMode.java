package main.modes;

import io.out.DisplayStandards;
import io.out.GUIManager;
import main.Session;
import resources.glyph.image.GlyphString;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

public class TransitiveTextMode implements OperatingMode {

    private final String text;
    private final OperatingMode targetMode;

    private final ArrayList<String> displayedText;

    private int displayFromIndex = 0;

    public TransitiveTextMode(String text, OperatingMode targetMode){
        this.text = text;
        this.targetMode = targetMode;
        ArrayList<String> breakByNewLine = new ArrayList<>();
        while (text.contains("\n")){
            int index = text.indexOf('\n');
            breakByNewLine.add(text.substring(0, index));
            do {
                text = text.substring(text.indexOf('\n') + 1);
            } while (text.charAt(1) == '\n');
        }
        displayedText = breakByNewLine;
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
        switch (ke.getKeyCode()) {
            case VK_ENTER:
                Session.getModeManager().revert(); //remove this mode from the stack - we do not need to return to it
                Session.getModeManager().transitionTo(targetMode); //then transition to the target mode
                return;
            case VK_UP: case VK_NUMPAD8:
                --displayFromIndex;
                if (displayFromIndex < 0) displayFromIndex = 0;
                break;
            case VK_DOWN: case VK_NUMPAD2:
                ++displayFromIndex;
                if (displayFromIndex >= displayedText.size()) displayFromIndex = displayedText.size() - 1;
                break;
        }
        out();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        gm.printCenteredLine(
                4,
                "[Press <Enter> to continue, or use the arrow keys to scroll]",
                DisplayStandards.TEXT_DEFAULT_BACKGROUND,
                DisplayStandards.TEXT_SUBDUED_FOREGROUND
        );
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        for (int i = displayFromIndex; i < displayedText.size(); ++i) {
            stringBuilder.append('\n').append(displayedText.get(i));
        }
        gm.printGlyphString(
                4,
                4,
                new GlyphString(stringBuilder.toString(), DisplayStandards.TEXT_DEFAULT_BACKGROUND, DisplayStandards.TEXT_DEFAULT_FOREGROUND)
        );
    }

    @Override
    public void from() {
        //no special cleanup required
    }
}
