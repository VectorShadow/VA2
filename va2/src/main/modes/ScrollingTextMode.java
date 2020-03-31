package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import main.Session;
import resources.glyph.GlyphString;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

/**
 * A mode for displaying text that may exceed the size of a single screen.
 */
public class ScrollingTextMode implements OperatingMode {

    private final String text;

    private final ArrayList<String> displayedText;

    private int displayFromIndex = 0;

    public ScrollingTextMode(String text){
        this.text = text;
        ArrayList<String> breakByNewLine = new ArrayList<>();
        while (text.contains("\n")){
            int index = text.indexOf('\n');
            breakByNewLine.add(text.substring(0, index));
            do {
                text = text.substring(text.indexOf('\n') + 1);
            } while (text.charAt(1) == '\n');
        }
        breakByNewLine.add(text); //ensure we add the line after the final line break
        displayedText = breakByNewLine;
    }

    @Override
    public InputCommandList getInput() {
        return null;
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(this, ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
        switch (ke.getKeyCode()) {
            case VK_ENTER: case VK_ESCAPE:
                Session.getModeManager().revert();
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
        int pct = (int)(100 * (double)displayFromIndex / ((double)displayedText.size() - 1.0));
        gm.printHighlightedCenteredLine(
                4,
                "[Press <Enter> to continue, or use the arrow keys to scroll (" + pct + "%)]",
                Session.getColorScheme()
        );
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('\n');
        for (int i = displayFromIndex; i < displayedText.size(); ++i) {
            stringBuilder.append('\n').append(displayedText.get(i));
        }
        gm.printGlyphString(
                4,
                4,
                new GlyphString(
                        stringBuilder.toString(),
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
    }

    @Override
    public void from() {
        //no special cleanup required
    }
}
