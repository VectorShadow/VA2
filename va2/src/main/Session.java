package main;

import engine.Engine;
import floor.Floor;
import io.file.FileManager;
import io.out.GUIManager;
import main.modes.ModeManager;
import util.Coordinate;
import world.actor.Actor;


/**
 * Maintains all single instance session tools in one location.
 * Operates as a singleton class, so all other single instance tools need not.
 */
public class Session {

    private static Camera camera;
    private static Floor currentFloor;
    private static Engine engine;
    private static FileManager fileManager;
    private static GUIManager guiManager;
    private static ModeManager modeManager;
    private static Actor playerActor;

    static void start() {
        camera = new Camera();
        currentFloor = null;
        engine = new Engine();
        fileManager = new FileManager();
        guiManager = new GUIManager();
        modeManager = new ModeManager();
        playerActor = null;
    }

    public static Camera getCamera() {
        return camera;
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

    public static ModeManager getModeManager() {
        return modeManager;
    }
    public static Actor getPlayerActor() {
        return playerActor;
    }

    public static void setCurrentFloor(Floor f) {
        currentFloor = f;
        //todo - hack - place the player on the new floor
        f.placeActor(playerActor, new Coordinate(0,0));
        engine.resetActors();
    }
    public static void setPlayerActor(Actor a) {
        playerActor = a;
    }
}
