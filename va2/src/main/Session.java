package main;

import io.file.FileManager;
import io.out.GUIManager;
import main.modes.ModeManager;


/**
 * Maintains all single instance session tools in one location.
 * Operates as a singleton class, so all other single instance tools need not.
 */
public class Session {

    private static FileManager fileManager;
    private static GUIManager guiManager;
    private static ModeManager modeManager;

    static void start() {
        fileManager = new FileManager();
        guiManager = new GUIManager();
        modeManager = new ModeManager();
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
}
