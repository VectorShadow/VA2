package main.modes;

import io.out.FloorRenderer;
import io.out.GUIManager;
import main.Cursor;
import main.Session;
import util.Coordinate;
import util.Direction;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class MainGameCursorMode extends MainGameViewMode {

    Cursor cursor;

    @Override
    public void to() {
        Session.getCamera().trackPlayer();
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
        cursor = new Cursor();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke)) return;
        switch (ke.getKeyCode()) {
            case VK_UP: case VK_NUMPAD8:
                cursor.shift(Direction.NORTH);
                break;
            case VK_NUMPAD9:
                cursor.shift(Direction.NORTH_EAST);
                break;
            case VK_RIGHT: case VK_NUMPAD6:
                cursor.shift(Direction.EAST);
                break;
            case VK_NUMPAD3:
                cursor.shift(Direction.SOUTH_EAST);
                break;
            case VK_DOWN: case VK_NUMPAD2:
                cursor.shift(Direction.SOUTH);
                break;
            case VK_NUMPAD1:
                cursor.shift(Direction.SOUTH_WEST);
                break;
            case VK_LEFT: case VK_NUMPAD4:
                cursor.shift(Direction.WEST);
                break;
            case VK_NUMPAD7:
                cursor.shift(Direction.NORTH_WEST);
                break;
            case VK_NUMPAD5:
                cursor.self();
                break;
            case VK_ESCAPE:
                Session.getModeManager().revert();
                return;
            case VK_ADD:
                cursor.cycle(true);
                break;
            case VK_SUBTRACT:
                cursor.cycle(false);
                break;
        }
        out();
    }
    @Override
    public void out() {
        super.out();
        Coordinate displayCursor = Session.getFloorRenderer().floorToDisplay(cursor.at());
        Session.getGuiManager().printGlyph(displayCursor.getRow(), displayCursor.getColumn(), cursor.draw());
    }

    @Override
    public void from() {
        //override - no need to reset
    }
}
