package io.out;

import contract.Gui;
import core.DualityGUI;
import error.ErrorLogger;
import main.Meta;
import resources.DualityContext;
import resources.DualityMode;
import resources.glyph.image.GlyphString;
import resources.glyph.image.ImageManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Primary agent for GUI Management.
 */
public class GUIManager {
    private static final String GFX_DIR = "./gfx";
    private static final String FRAME_ICON_FILE_NAME = GFX_DIR + "/Icon.png";
    private static final File FULLSCREEN_GFX_FILE= new File(GFX_DIR + "/32.png");
    private static final File WINDOWED_GFX_FILE = new File(GFX_DIR + "/16.png");

    public static final int CHANNEL_GAME = 0;
    public static final int CHANNEL_FULLSCREEN_TEXT = 1;

    private final Gui GUI = new DualityGUI();
    /**
     * Loops to redraw the screen.
     */
    private class ScreenRefresherDaemon extends Thread {
        private static final int FRAMES_PER_SECOND = 40;
        private static final int ONE_SECOND = 1000;

        public void run() {
            loop();
        }

        private int refreshRate() {
            return ONE_SECOND / FRAMES_PER_SECOND;
        }

        private void loop() {
            for (;;){
                GUI.redraw();
                try {
                    Thread.sleep(refreshRate());
                } catch (InterruptedException e) {
                    ErrorLogger.logFatalException(e);
                }
            }
        }
    }

    public GUIManager() {
        GUI.setFullScreen(false);
        try {
            ImageManager.loadGraphics(DualityContext.TILE_FULLSCREEN, FULLSCREEN_GFX_FILE);
            ImageManager.loadGraphics(DualityContext.TILE_WINDOWED, WINDOWED_GFX_FILE);
        } catch (IOException e) {
            ErrorLogger.logFatalException(e);
        }
        //todo - add a keylistener. from ACE: Display.setKeyListener(new ACEListener());
        GUI.setIcon(FRAME_ICON_FILE_NAME);
        GUI.setTitle(Meta.gameTitle() + " - " + Meta.version());
        setupChannels();
        //todo - additional setup?
        new ScreenRefresherDaemon().start();
    }
    private void setupChannels() {
        //todo - game channel setup here
        int fsText = GUI.addChannel(DualityMode.TEXT);
        if (CHANNEL_FULLSCREEN_TEXT != fsText)
            throw new IllegalStateException("Invalid channel index for Fullscreen Text - expected " +
                    CHANNEL_FULLSCREEN_TEXT + " but was " + fsText);
    }

    public void changeChannelToGameDisplay() {
        GUI.changeChannel(CHANNEL_GAME);
    }
    public void changeChannelToFullscreenText() {
        GUI.changeChannel(CHANNEL_FULLSCREEN_TEXT);
    }

    public void clearScreen() {
        GUI.clear();
    }
    public void clearZone(int zone) {
        GUI.clear(zone);
    }
    public void printCenteredBlock(double percentFromTop, String[] text, Color foreground, Color background) {
        int row = GUI.rowAtPercent(percentFromTop);
        for (int i = 0; i < text.length; ++i)
            printCenteredLine(row++, text[i], foreground, background);
    }
    public void printCenteredLine(double percentFromTop, String text, Color foreground, Color background) {
        printCenteredLine(GUI.rowAtPercent(percentFromTop), text, foreground, background);
    }
    public void printCenteredLine(int row, String text, Color foreground, Color background) {
        GUI.printCentered(row, new GlyphString(text, foreground, background));
    }
}
