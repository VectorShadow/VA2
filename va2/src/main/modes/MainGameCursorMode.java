package main.modes;

import io.in.InputCommandList;
import io.out.GUIManager;
import main.Cursor;
import main.Session;
import util.Coordinate;
import util.Direction;
import world.dungeon.floor.FloorTile;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import static io.in.InputCommandListDefinitions.*;

public class MainGameCursorMode extends MainGameViewMode {

    Cursor cursor;

    @Override
    public InputCommandList getInput() {
        return MAIN_GAME_CURSOR_MODE;
    }

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
        switch (getInput().test(ke)) {
            case CURSOR_CYCLE_FAR:
                cursor.cycle(true);
                break;
            case CURSOR_CYCLE_NEAR:
                cursor.cycle(false);
                break;
            case CURSOR_EXIT:
                Session.getModeManager().revert();
                return;
                default:
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
                    }
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
    public String describeCursor() {
        FloorTile ft = Session.getCurrentFloor().tileAt(cursor.at().getRow(), cursor.at().getColumn());
        return ft.getActor() == null
                ? ft.getTerrain().getTEMPLATE().getDescription()
                : ft.getActor().getTEMPLATE().getDescription();
    }
}
