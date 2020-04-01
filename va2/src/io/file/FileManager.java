package io.file;

import engine.Engine;
import error.ErrorLogger;
import main.Camera;
import main.Player;
import main.Session;
import main.progression.estate.EstateProgression;
import world.dungeon.Dungeon;
import world.dungeon.floor.Floor;
import world.item.inventory.Inventory;
import world.lore.LoreDefinitions;
import world.lore.LoreTree;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Primary agent for file I/O.
 */
public class FileManager {

    private static final Path SAVE_DIRECTORY = Paths.get("./SaveData");
    private static final Path PROFILE_FILE = Paths.get(SAVE_DIRECTORY + "/profile.sav");
    private static final Path GAME_FILE = Paths.get(SAVE_DIRECTORY + "/game.sav");
    /*todo - methods: */ //private static final Path AUTOPSY_FILE = Paths.get(SAVE_DIRECTORY + "/autopsy.txt");
    /*todo - autopsy implementation */ //private static final long SIGNATURE = 8829287429145021153L;

    private void closeStreams(Closeable fileStream, Closeable objectStream) {
        if (fileStream != null) {
            try {
                fileStream.close();
            } catch (IOException e) {
                ErrorLogger.logFatalException(e);
            }
        }
        if (objectStream != null) {
            try {
                objectStream.close();
            } catch (IOException e) {
                ErrorLogger.logFatalException(e);
            }
        }
    }
    /**
     * Delete the existing saved game file.
     */
    public void deleteSavedGame() {
        if (Files.exists(GAME_FILE)) {
            try {
                Files.delete(GAME_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Detect whether the save directory exists. If it does not, create it.
     */
    private void ensureDirectory() {
        if (!Files.exists(SAVE_DIRECTORY)) {
            try {
                Files.createDirectory(SAVE_DIRECTORY);
            } catch (IOException e) {
                ErrorLogger.logFatalException(e);
            }
        }
    }
    /**
     * Detect whether a player profile file exists. If it does not, create it.
     * @returns whether an existing profile was found.
     */
    private boolean ensureProfile() {
        ensureDirectory();
        if (!Files.exists(PROFILE_FILE)) {
            deleteSavedGame(); //if a profile does not exist but a saved game does, remove the saved game.
            try {
                Files.createFile(PROFILE_FILE);
            } catch (IOException e) {
                ErrorLogger.logFatalException(e);
            }
            return false;
        }
        return true;
    }
    /**
     * Detect whether a saved game file exists. If it does not, create it.
     * @returns whether a saved game file was found.
     */
    private boolean ensureSavedGame() {
        ensureDirectory();
        if (!Files.exists(GAME_FILE)) {
            try {
                Files.createFile(GAME_FILE);
            } catch (IOException e) {
                ErrorLogger.logFatalException(e);
            }
            return false;
        }
        return true;
    }

    /**
     * Attempt to load an existing player profile.
     * @returns whether an existing profile was found.
     */
    public boolean loadProfile() {
        if (!ensureProfile()) return false; //no profile existed, nothing to do here
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(PROFILE_FILE.toString());
            ois = new ObjectInputStream(fin);
            LoreTree unlockedLore = (LoreTree)ois.readObject();
            Inventory legacyResources = (Inventory)ois.readObject();
            LoreDefinitions.setLockTree(unlockedLore);
            Session.setLegacyResources(legacyResources);
        } catch (EOFException eofe) {
            return false; //tried to load an empty profile - it was created but never written to
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeStreams(fin, ois);
        }
        return true;
    }
    public boolean loadSavedGame() {
        if (!ensureSavedGame()) return false; //no saved game existed, nothing to do here
        FileInputStream fin = null;
        ObjectInputStream ois = null;
        try {
            fin = new FileInputStream(GAME_FILE.toString());
            ois = new ObjectInputStream(fin);
            Camera camera = (Camera)ois.readObject();
            Dungeon dungeon = (Dungeon)ois.readObject();
            EstateProgression estateProgression = (EstateProgression)ois.readObject();
            Floor floor = (Floor)ois.readObject();
            Engine engine = (Engine)ois.readObject();
            Player player = (Player)ois.readObject();
            Session.loadSavedGame(camera, dungeon, estateProgression, floor, engine, player);
        } catch (EOFException eofe) {
            return false; //tried to load an empty saved game - it was created but never written to
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeStreams(fin, ois);
        }
        return true;
    }
    public void saveProfile() {
        ensureProfile();
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream(PROFILE_FILE.toString());
            oos = new ObjectOutputStream(fout);
            oos.writeObject(LoreDefinitions.getLockTree());
            oos.writeObject(Session.getLegacyResources());
        } catch (Exception e) {
            ErrorLogger.logFatalException(e);
        } finally {
            closeStreams(fout, oos);
        }
    }
    public void saveGameState() {
        ensureSavedGame();
        saveProfile();
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream(GAME_FILE.toString());
            oos = new ObjectOutputStream(fout);
            oos.writeObject(Session.getCamera());
            oos.writeObject(Session.getCurrentDungeon());
            oos.writeObject(Session.getEstateProgression());
            oos.writeObject(Session.getCurrentFloor());
            oos.writeObject(Session.getEngine());
            oos.writeObject(Session.getPlayer());
        } catch (Exception e) {
            ErrorLogger.logFatalException(e);
        } finally {
            closeStreams(fout, oos);
        }
    }
}
