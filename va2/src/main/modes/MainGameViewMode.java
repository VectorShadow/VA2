package main.modes;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import io.out.FloorRenderer;
import io.out.GUIManager;
import io.out.message.MessageType;
import main.Session;
import util.Coordinate;
import util.Direction;
import world.actor.Actor;
import world.actor.ActorDefinitions;
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
        switch (ke.getKeyCode()) {
            case VK_UP: case VK_NUMPAD8:
                action = new AdjacentMovementAction(Direction.NORTH);
                break;
            case VK_NUMPAD9:
                action = new AdjacentMovementAction(Direction.NORTH_EAST);
                break;
            case VK_RIGHT: case VK_NUMPAD6:
                action = new AdjacentMovementAction(Direction.EAST);
                break;
            case VK_NUMPAD3:
                action = new AdjacentMovementAction(Direction.SOUTH_EAST);
                break;
            case VK_DOWN: case VK_NUMPAD2:
                action = new AdjacentMovementAction(Direction.SOUTH);
                break;
            case VK_NUMPAD1:
                action = new AdjacentMovementAction(Direction.SOUTH_WEST);
                break;
            case VK_LEFT: case VK_NUMPAD4:
                action = new AdjacentMovementAction(Direction.WEST);
                break;
            case VK_NUMPAD7:
                action = new AdjacentMovementAction(Direction.NORTH_WEST);
                break;
            case VK_NUMPAD5:
                action = new PauseAction();
                break;
            case VK_COMMA: //attempt to leave the current dungeon, or enter an estate room
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    here = Session.getPlayer().getActor().getLocation();
                    tt = (TerrainTemplate)
                            Session.getCurrentFloor().tileAt(here.getRow(), here.getColumn()).getTerrain().getTemplate();
                    if (tt.equals(TerrainDefinitions.FLIGHT_STAIR)) {
                        Session.getCurrentDungeon().exitDungeon(false);
                    } else if (tt.equals(TerrainDefinitions.REWARD_STAIR)) {
                        Session.getCurrentDungeon().exitDungeon(true);
                    } //todo - else if [any of the estate room portals]
                }
                break;
            case VK_PERIOD: //attempt to progress the current dungeon
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    here = Session.getPlayer().getActor().getLocation();
                    tt = (TerrainTemplate)
                            Session.getCurrentFloor().tileAt(here.getRow(), here.getColumn()).getTerrain().getTemplate();
                    if (tt.equals(TerrainDefinitions.FOREST_GATE))
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
                    Session.getMessageCenter().sendMessage("Ingame help is not yet implemented.", MessageType.ERROR);
                }
                break;
            case VK_Q:
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    Session.getModeManager().transitionTo(new ConfirmLastInputMode());
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
        Session.resetMessageCenter();
    }
}
