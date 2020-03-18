package main.modes;

import engine.action.Action;
import engine.action.AdjacentMovementAction;
import engine.action.PauseAction;
import io.out.FloorRenderer;
import io.out.GUIManager;
import io.out.message.MessageCenter;
import io.out.message.MessageType;
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
        MessageCenter mc = Session.getMessageCenter();
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
                    if (tt.equals(TerrainDefinitions.FLIGHT_STAIR) ||
                            (tt.equals(TerrainDefinitions.REWARD_STAIR) &&
                                    Session.getCurrentFloor().isFloorBossAlive())) { //kill floor boss for rewards!
                        Session.getCurrentDungeon().exitDungeon(false);
                    } else if (tt.equals(TerrainDefinitions.REWARD_STAIR) &&
                            !Session.getCurrentFloor().isFloorBossAlive()) {
                        Session.getCurrentDungeon().exitDungeon(true);
                    } else if(!Session.getCurrentDungeon().isDungeonBossAlive() &&
                            tt.equals(Session.getCurrentDungeon().getTheme().getTerrainSet().getEndTerrain())) {
                        //todo - unlock and display dungeon completion lore
                        Session.getCurrentDungeon().exitDungeon(true);
                    } else {
                        if (EstateRoomMenuMode.interpretTerrain(tt) != null)
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
                    if (tt.equals(TerrainDefinitions.FOREST_GATE)) {
                        //todo - open a menu here once we have cleared Dark Grove
                        Session.getCurrentDungeon().nextFloor();
                    } else if (tt.equals(TerrainDefinitions.NEXT_FLOOR_STAIR)) {
                        if (Session.getCurrentFloor().isFloorBossAlive()) {
                            mc.sendMessage(
                                    "You may not enter the next floor before defeating the guardian of this floor.",
                                    MessageType.ERROR,
                                    MessageCenter.PRIORITY_MAX);
                        } else
                            Session.getCurrentDungeon().nextFloor();
                    } else if(!Session.getCurrentDungeon().isDungeonBossAlive() &&
                            tt.equals(Session.getCurrentDungeon().getTheme().getTerrainSet().getEndTerrain())) {
                        //todo - unlock and display dungeon completion lore
                        Session.getCurrentDungeon().exitDungeon(true);
                    }
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
            case VK_R:
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    Session.getModeManager().transitionTo(new MessageRecallMode(Session.getMessageCenter().getMessageRecall()));
                }
                return;
            case VK_V:
                if (ke.getModifiersEx() == SHIFT_DOWN_MASK) {
                    mc.increasePriorityThreshold();
                    mc.sendMessage(
                            "Message Center - now displaying " +
                                    (mc.getPriorityThreshold() == MessageCenter.PRIORITY_HIGH
                                            ? "messages of priority [High] and above."
                                            : mc.getPriorityThreshold() == MessageCenter.PRIORITY_LOW
                                            ? "messages of priority [Low] and above."
                                            : "all messages."),
                            MessageType.GAME,
                            MessageCenter.PRIORITY_MAX
                    );
                } else {
                    mc.decreasePriorityThreshold();
                    mc.sendMessage(
                            "Message Center - now displaying messages of priority " +
                                    (mc.getPriorityThreshold() == MessageCenter.PRIORITY_HIGH
                                            ? "[High] and above."
                                            : mc.getPriorityThreshold() == MessageCenter.PRIORITY_LOW
                                            ? "[Low] and above."
                                            : "[Maximum] only."),
                            MessageType.GAME,
                            MessageCenter.PRIORITY_MAX
                    );
                }
                break;
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
        gm.printPlayerStatistics();
        gm.printMessages();
    }

    @Override
    public void from() {
        Session.reset();
    }
}
