package main.modes;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import world.dungeon.floor.Floor;
import io.out.FloorRenderer;
import io.out.GUIManager;
import main.Session;
import util.Coordinate;
import util.Direction;
import world.actor.Actor;
import world.actor.ActorDefinitions;
import world.dungeon.generate.PredefinedLevelGenerator;
import world.dungeon.theme.ThemeDefinitions;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * The primary game display mode.
 */
public class MainGameViewMode implements OperatingMode {

    FloorRenderer floorRenderer;

    public MainGameViewMode() {
    }

    @Override
    public void to() {
        //hack - generate the player estate
        Session.setCurrentFloor(new PredefinedLevelGenerator().generate(ThemeDefinitions.YSIAN_ESTATE, 0));
        //hack - generate a test ai
        Actor aiTest = new Actor(ActorDefinitions.PLAYER_TEMPLATE);
        Session.addActor(aiTest, new Coordinate(8,8));
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToGameDisplay();
        floorRenderer = new FloorRenderer();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke)) return;
        Action action = null;
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
    }

    @Override
    public void from() {
        //nothing to do
    }
}
