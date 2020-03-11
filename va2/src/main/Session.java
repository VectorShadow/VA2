package main;

import engine.Engine;
import engine.action.ActionDefinitions;
import io.out.DisplayStandards;
import io.out.message.MessageCenter;
import main.modes.OperatingMode;
import main.modes.ScrollingTextMode;
import main.modes.TransitiveScrollingTextMode;
import resources.chroma.ChromaSet;
import world.actor.ActorDefinitions;
import world.dungeon.Dungeon;
import world.dungeon.DungeonDefinitions;
import world.dungeon.floor.Floor;
import io.file.FileManager;
import io.out.GUIManager;
import main.modes.ModeManager;
import util.Coordinate;
import world.actor.Actor;
import world.dungeon.floor.FloorTile;
import world.dungeon.theme.ThemeDefinitions;
import world.lore.LockLeaf;
import world.lore.LoreDefinitions;
import world.lore.LoreTree;

import java.util.Random;


/**
 * Maintains all single instance session tools in one location.
 * Operates as a singleton class, so all other single instance tools need not.
 */
public class Session {

    private static final Random RNG = new Random();

    private static Camera camera;
    private static ChromaSet colorScheme;
    private static Dungeon currentDungeon;
    private static Floor currentFloor;
    private static Engine engine;
    private static FileManager fileManager;
    private static GUIManager guiManager;
    private static LoreTree lore;
    private static MessageCenter messageCenter;
    private static ModeManager modeManager;
    private static Player player;

    static void start() {
        colorScheme = DisplayStandards.THEME_THE_DARK_GROVE;
        fileManager = new FileManager();
        guiManager = new GUIManager();
        modeManager = new ModeManager();
        lore = LoreDefinitions.LOCK_TREE;
        reset();
    }
    public static void reset() {
        camera = new Camera();
        currentDungeon = new Dungeon(DungeonDefinitions.THE_DARK_GROVE);
        currentFloor = new Floor(0, ThemeDefinitions.YSIAN_ESTATE);
        engine = new Engine();
        messageCenter = new MessageCenter();
        player = new Player();
        player.setActor(new Actor(ActorDefinitions.PLAYER_TEMPLATE));
        currentFloor.generate();
    }
    public static void loadProfile(LoreTree loreTree) {
        lore = loreTree;
    }
    public static void loadSavedGame(Camera c, Dungeon d, Floor f, Engine e, Player p) {
        camera = c;
        currentDungeon = d;
        currentFloor = f;
        engine = e;
        player = p;
    }

    public static Random getRNG() {
        return RNG;
    }

    public static Camera getCamera() {
        return camera;
    }

    public static ChromaSet getColorScheme() {
        return colorScheme;
    }

    public static Dungeon getCurrentDungeon() {
        return currentDungeon;
    }

    public static Floor getCurrentFloor() {
        return currentFloor;
    }

    public static Engine getEngine() {
        return engine;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static GUIManager getGuiManager() {
        return guiManager;
    }

    public static MessageCenter getMessageCenter() {
        return messageCenter;
    }

    public static ModeManager getModeManager() {
        return modeManager;
    }
    public static LoreTree getLore() {
        return lore;
    }
    public static void unlockLore(int themeIndex, int loreIndex, OperatingMode targetMode) {
        ((LockLeaf)lore.get(themeIndex, loreIndex)).unlock();
        if (targetMode == null)
            modeManager.transitionTo(
                    new ScrollingTextMode(LoreDefinitions.loreAt(themeIndex, loreIndex)));
        else
            modeManager.transitionTo(
                    new TransitiveScrollingTextMode(LoreDefinitions.loreAt(themeIndex, loreIndex), targetMode)
        );
    }
    public static Player getPlayer() {
        return player;
    }

    public static void setColorScheme(ChromaSet colorScheme) {
        Session.colorScheme = colorScheme;
    }

    public static void setCurrentFloor(Floor f) {
        currentFloor = f;
        engine.resetActors();
        f.generate();
        Actor playerActor = player.getActor();
        playerActor.consumeEnergy(0 - ActionDefinitions.MAXIMUM_ACTION_ENERGY); //give the player the initiative
        currentFloor.placeActor(playerActor, currentFloor.getPlayerSpawn());
        engine.addActor(playerActor, true);
    }
    public static void setCurrentDungeon(Dungeon d) {
        currentDungeon = d;
        currentDungeon.nextFloor();
    }
    public static void addActor(Actor a, Coordinate c) {
        currentFloor.placeActor(a, c);
        engine.addActor(a);
    }
    public static void removeActor(Actor a) {
        Coordinate c = a.getLocation();
        FloorTile ft = currentFloor.tileAt(c.getRow(), c.getColumn());
        engine.removeActor(a);
        ft.setActor(null);
    }
    public static void killActor(Actor a) {
        removeActor(a);
        if (player.getActor() == a) {
            System.exit(0);
            //todo - terminate the game appropriately
        }
    }
    public static boolean isPlaying() {
        return player.getActor() != null;
    }
}
