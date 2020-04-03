package main;

import engine.Engine;
import engine.action.ActionDefinitions;
import io.out.DisplayStandards;
import io.out.FloorRenderer;
import io.out.message.MessageCenter;
import main.modes.*;
import main.progression.estate.EstateProgression;
import main.progression.rewards.Experience;
import resources.chroma.ChromaSet;
import resources.glyph.image.ImageManager;
import world.actor.ActorDefinitions;
import world.dungeon.Dungeon;
import world.dungeon.floor.Floor;
import io.file.FileManager;
import io.out.GUIManager;
import util.Coordinate;
import world.actor.Actor;
import world.dungeon.floor.FloorTile;
import world.dungeon.theme.ThemeDefinitions;
import world.item.define.ItemDefinitions;
import world.item.inventory.Inventory;
import world.lore.LockLeaf;
import world.lore.LoreDefinitions;

import java.util.Random;


/**
 * Maintains all single instance session tools in one location.
 * Operates as a singleton class, so all other single instance tools need not.
 */
public class Session {

    private static final Random RNG = new Random();

    private static boolean fullScreen;
    private static boolean graphics;

    private static Camera camera;
    private static ChromaSet colorScheme;
    private static Dungeon currentDungeon;
    private static Floor currentFloor;
    private static Engine engine;
    private static EstateProgression estateProgression;
    private static FileManager fileManager;
    private static FloorRenderer floorRenderer;
    private static GUIManager guiManager;
    private static Inventory legacyResources;
    private static MessageCenter messageCenter;
    private static ModeManager modeManager;
    private static Player player;
    private static TargetList targetList;

    static void start() {
        ItemDefinitions.checkOrder();
        Experience.fillXPTable();
        colorScheme = DisplayStandards.THEME_THE_DARK_GROVE;
        estateProgression = new EstateProgression();
        fileManager = new FileManager();
        fullScreen = false;
        graphics = false;
        guiManager = new GUIManager();
        legacyResources = new Inventory(500); //todo - replace with constants defined somewhere!
        modeManager = new ModeManager();
        reset();
    }
    public static void reset() {
        camera = new Camera();
        currentDungeon = new Dungeon(ThemeDefinitions.DUNGEON_THEMES[ThemeDefinitions.DARK_GROVE]);
        currentFloor = new Floor(0, ThemeDefinitions.DUNGEON_THEMES[ThemeDefinitions.YSIAN_ESTATE]);
        engine = new Engine();
        messageCenter = new MessageCenter();
        player = new Player();
        player.setActor(new Actor(ActorDefinitions.PLAYER_TEMPLATE));
        player.getEquipment().setStartingItems();
        player.getActor().getCombatant().renewHealth();
        player.getActor().getCombatant().renewSanity();
        player.getActor().getCombatant().renewSoul();
        currentFloor.generate();
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

    public static EstateProgression getEstateProgression() { return estateProgression; }

    public static Engine getEngine() {
        return engine;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static FloorRenderer getFloorRenderer() {
        return floorRenderer;
    }

    public static GUIManager getGuiManager() {
        return guiManager;
    }

    public static Inventory getLegacyResources() { return legacyResources; }

    public static MessageCenter getMessageCenter() {
        return messageCenter;
    }

    public static ModeManager getModeManager() {
        return modeManager;
    }
    public static void unlockLore(int themeIndex, int loreIndex, OperatingMode targetMode) {
        ((LockLeaf)LoreDefinitions.getLockTree().get(themeIndex, loreIndex)).unlock();
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

    public static TargetList getTargetList() {
        return targetList;
    }
    public static void newTargetList() {
        targetList = new TargetList();
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
        if (((LockLeaf)LoreDefinitions.getLockTree().get(
                LoreDefinitions.themeIndex(f.THEME),
                LoreDefinitions.bracketLoreIndex(f.THEME, true)
        )).isLocked()) {
            unlockLore(
                    LoreDefinitions.themeIndex(f.THEME),
                    LoreDefinitions.bracketLoreIndex(f.THEME, true),
                    null);
        }
    }
    public static void setCurrentDungeon(Dungeon d) {
        currentDungeon = d;
        currentDungeon.nextFloor();
    }

    public static void setEstateProgression(EstateProgression ep) {
        estateProgression = ep;
    }

    public static void setLegacyResources(Inventory lr) {
        legacyResources = lr;
    }
    public static void startFloorRenderer() {
        floorRenderer = new FloorRenderer();
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
        killActor(a, false);
    }
    public static void killActor(Actor a, boolean win) {
        a.clearQueuedActions();
        removeActor(a);
        if (player.getActor() == a) {
            getFileManager().saveProfile();
            getFileManager().deleteSavedGame();
            reset();
            getModeManager().revert();
            getModeManager().transitionTo(
                    new ScrollingTextMode(
                            win
                                    ? "Congratulations! You have completed this version of Chronicles of the Abyss! " +
                                    "The story will continue in future releases."
                                    : "You have died!"
                    )
            );
            //todo - generate an autopsy file, score the game, etc.
        } else if (a == targetList.get())
            targetList.clearTarget();
    }
    public static boolean isPlaying() {
        return player.getActor() != null;
    }
    public static boolean isFinalFloor() {
        return currentFloor.DEPTH == currentDungeon.getTheme().getDepth();
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setFullScreen(boolean fullScreen) {
        if (Session.fullScreen != fullScreen)
            toggleFullscreenMode();
    }

    public static void toggleFullscreenMode() {
        guiManager.toggleFullScreenMode();
        fullScreen = !fullScreen;
    }

    public static boolean isGraphics() {
        return graphics;
    }

    public static void setGraphics(boolean graphics) {
        if (Session.graphics != graphics)
            toggleGraphicsMode();
    }
    public static void toggleGraphicsMode() {
        ImageManager.toggleGraphics();
        graphics = !graphics;
    }
}
