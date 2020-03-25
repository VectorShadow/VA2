package main.modes.menu;

import contract.menu.Menu;
import io.in.InputCommandList;
import io.in.KeyCodeInterpreter;
import io.out.GUIManager;
import main.Session;
import main.modes.OperatingMode;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public abstract class MenuMode implements OperatingMode {
    protected Menu menu;

    @Override
    public InputCommandList getInput() {
        return null;
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke) || (ke.getModifiersEx() == ALT_DOWN_MASK)) return;
        int kc = ke.getKeyCode();
        switch (kc) {
            case VK_UP:
                menu.regress();
                break;
            case VK_DOWN:
                menu.advance();
                break;
            case VK_ENTER:
                handleMenuOptionIndex(menu.getSelectedOptionIndex());
                return;
            case VK_0: case VK_1:
            case VK_2: case VK_3:
            case VK_4: case VK_5:
            case VK_6: case VK_7:
            case VK_8: case VK_9:
            case VK_NUMPAD0: case VK_NUMPAD1:
            case VK_NUMPAD2: case VK_NUMPAD3:
            case VK_NUMPAD4: case VK_NUMPAD5:
            case VK_NUMPAD6: case VK_NUMPAD7:
            case VK_NUMPAD8: case VK_NUMPAD9:
            case VK_A: case VK_B:
            case VK_C: case VK_D:
            case VK_E: case VK_F:
                int menuIndex = KeyCodeInterpreter.toInt(kc) - 1;
                if (menuIndex < menu.size() && menu.isEnabled(menuIndex))
                    handleMenuOptionIndex(menuIndex);
                return;
            case VK_ESCAPE:
                Session.getModeManager().revert();
                return;
        }
        out();
    }
    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        gm.printMenu(0.35, menu);
    }

    protected abstract void handleMenuOptionIndex(int index);
    protected void setMenu(Menu m) {
        menu = m;
    }
}
