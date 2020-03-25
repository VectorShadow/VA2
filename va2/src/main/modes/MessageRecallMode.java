package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import io.out.message.MessageRecall;
import main.Session;

import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.ALT_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

public class MessageRecallMode implements OperatingMode {
    MessageRecall messageRecall;

    private int displayFromIndex = 0;

    public MessageRecallMode(MessageRecall mr){
        messageRecall = mr;
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
        if (OperatingMode.overrideHandleInput(ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
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
                if (displayFromIndex >= messageRecall.size()) displayFromIndex = messageRecall.size() - 1;
                break;
        }
        out();
    }
    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        int pct = (int)(100 * (double)displayFromIndex / ((double)messageRecall.size() - 1.0));
        gm.printHighlightedCenteredLine(
                4,
                "[Press <Enter> to continue, or use the arrow keys to scroll (" + pct + "%)]",
                Session.getColorScheme()
        );
        int row = 6;
        for (int i = displayFromIndex; i < messageRecall.size(); ++i) {
            if (row > gm.maxRow()) break;
            row = (gm.printGlyphString(row, 4, messageRecall.get(i).asGlyphString())).y + 1;
        }
    }

    @Override
    public void from() {
        //no special cleanup required
    }
}
