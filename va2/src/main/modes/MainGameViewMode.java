package main.modes;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import io.out.FloorRenderer;
import io.out.GUIManager;
import main.MetaData;
import main.Player;
import main.Session;
import main.modes.menu.EstateRoomMenuMode;
import util.Coordinate;
import util.Direction;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * The primary game display mode.
 */
public class MainGameViewMode implements OperatingMode {

    FloorRenderer floorRenderer;

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
        floorRenderer = new FloorRenderer();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke)) return;
        Action action = null;
        Coordinate here;
        TerrainTemplate tt;
        Player player = Session.getPlayer();
        switch (ke.getKeyCode()) {
            case VK_UP: case VK_NUMPAD8:
                action = player.getMove(Direction.NORTH);
                break;
            case VK_NUMPAD9:
                action = player.getMove(Direction.NORTH_EAST);
                break;
            case VK_RIGHT: case VK_NUMPAD6:
                action = player.getMove(Direction.EAST);
                break;
            case VK_NUMPAD3:
                action = player.getMove(Direction.SOUTH_EAST);
                break;
            case VK_DOWN: case VK_NUMPAD2:
                action = player.getMove(Direction.SOUTH);
                break;
            case VK_NUMPAD1:
                action = player.getMove(Direction.SOUTH_WEST);
                break;
            case VK_LEFT: case VK_NUMPAD4:
                action = player.getMove(Direction.WEST);
                break;
            case VK_NUMPAD7:
                action = player.getMove(Direction.NORTH_WEST);
                break;
            case VK_NUMPAD5:
                action = new PauseAction();
                break;
            case VK_COMMA: //attempt to leave the current dungeon, or enter an estate room
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    here = player.getActor().getLocation();
                    tt = (TerrainTemplate)
                            Session.getCurrentFloor().tileAt(here.getRow(), here.getColumn()).getTerrain().getTemplate();
                    if (tt.equals(TerrainDefinitions.FLIGHT_STAIR)) {
                        Session.getCurrentDungeon().exitDungeon(false);
                    } else if (tt.equals(TerrainDefinitions.REWARD_STAIR)) {
                        Session.getCurrentDungeon().exitDungeon(true);
                    } else {
                        if (EstateRoomMenuMode.interpretTerrain(tt) == null) break;
                        Session.getModeManager().transitionTo(EstateRoomMenuMode.interpretTerrain(tt));
                        return;
                    }
                }
                break;
            case VK_PERIOD: //attempt to progress the current dungeon
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    here = player.getActor().getLocation();
                    tt = (TerrainTemplate)
                            Session.getCurrentFloor().tileAt(here.getRow(), here.getColumn()).getTerrain().getTemplate();
                    if (tt.equals(TerrainDefinitions.FOREST_GATE)) //todo - open a menu here once we have cleared Dark Grove
                        Session.getCurrentDungeon().nextFloor();
                }
                break;
            case VK_S:
                if (ke.getModifiersEx() == CTRL_DOWN_MASK) {
                    Session.getFileManager().saveGameState();
                    Session.getModeManager().revert();
                }
                return;
            case VK_SLASH:
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    Session.getModeManager().transitionTo(new ScrollingTextMode(MetaData.inGameHelp()));
                }
                return;
            case VK_Q:
                if (ke.getModifiersEx() == CTRL_DOWN_MASK) {
                    Session.getModeManager().transitionTo(new ConfirmDeleteGameMode());
                }
                return;
            //todo - lots. breaks when we want to redraw the screen, returns if not
        }
        if (action != null)
            Session.getEngine().execute(action);
        out();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        //update the renderer's glyphMap from the current world.dungeon.floor before drawing it to the screen
        floorRenderer.update(Session.getCurrentFloor());
        for (int r = 0; r < floorRenderer.countRows(); ++r) {
            for (int c = 0; c < floorRenderer.countColumns(); ++c) {
                gm.printGlyph(r, c, floorRenderer.getGlyphAt(r, c));
            }
        }
        gm.printMessages();
    }

    @Override
    public void from() {
        Session.reset();
    }
}
