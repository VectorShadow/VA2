package io.out;

import contract.Gui;
import contract.menu.Menu;
import core.DualityGUI;
import error.ErrorLogger;
import io.in.InputCommand;
import io.in.InputCommandList;
import io.out.message.Message;
import main.MetaData;
import main.Player;
import main.Session;
import main.TargetList;
import main.progression.Experience;
import resources.DualityContext;
import resources.DualityMode;
import resources.chroma.ChromaSet;
import resources.glyph.Glyph;
import resources.glyph.GlyphString;
import resources.glyph.image.ImageManager;
import util.Format;
import world.actor.Actor;
import world.item.Armor;
import world.item.MeleeWeapon;
import world.item.loadout.Equipment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * Primary agent for GUI Management.
 */
public class GUIManager {
    /**
     * a key listener that throttles player input, preventing handling overload
     */
    private class ThrottledKeyListener implements KeyListener {
        private static final int MINIMUM_INPUT_INTERVAL = 15;
        private long lastValidInput = 0;

        @Override
        public void keyTyped(KeyEvent e) {
            //nothing to do
        }

        @Override
        public void keyPressed(KeyEvent e) {
            long now = System.currentTimeMillis();
            if (now - lastValidInput < MINIMUM_INPUT_INTERVAL) return;
            lastValidInput = now;
            try {
                Session.getModeManager().handleKeyPress(e);
            } catch (Exception ex) {
                ErrorLogger.logFatalException(ex);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //nothing to do
        }
    }
    private static final String GFX_DIR = "./gfx";
    private static final String FRAME_ICON_FILE_NAME = GFX_DIR + "/Icon.png";
    private static final File FULLSCREEN_GFX_FILE= new File(GFX_DIR + "/32.png");
    private static final File WINDOWED_GFX_FILE = new File(GFX_DIR + "/16.png");

    public static final int CHANNEL_GAME = 0;
    public static final int CHANNEL_FULLSCREEN_TEXT = 1;

    public static final int ZONE_MESSAGE_CENTER = 0;
    public static final int ZONE_PLAYER_STATUS = 1;
    public static final int ZONE_TARGET_STATUS = 2;
    public static final int ZONE_PLAYER_ACTIONS = 3;

    private final Gui GUI = new DualityGUI();

    private int messageWindowRows = 0;
    private int messageWindowCols = 0;
    /**
     * Loops to redraw the screen.
     */
    private class ScreenRefresherDaemon extends Thread {
        private static final int FRAMES_PER_SECOND = 8;
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
        GUI.addKeyListener(new ThrottledKeyListener());
        GUI.addWindowListener(new AutoSaveWindowListener());
        GUI.setIcon(FRAME_ICON_FILE_NAME);
        GUI.setTitle(MetaData.gameTitle() + " - " + MetaData.version());
        setupChannels();
        //todo - additional setup?
        new ScreenRefresherDaemon().start();
    }
    private void setupChannels() {
        final double TARGET_WINDOW_VERTICAL_ORIGIN = 0.39;
        final double MESSAGE_WINDOW_VERTICAL_ORIGIN = 0.78;
        int messageZone =
                GUI.addZone(
                        CHANNEL_GAME,
                        MESSAGE_WINDOW_VERTICAL_ORIGIN,
                        1.0 - MESSAGE_WINDOW_VERTICAL_ORIGIN,
                        0.0,
                        1.0,
                        DualityMode.MESSAGES
                );
        //calculate the number of rows and columns available to display messages:
        messageWindowRows = GUI.countRows() - GUI.rowAtPercent(MESSAGE_WINDOW_VERTICAL_ORIGIN);
        messageWindowCols = GUI.colAtPercent(ZONE_MESSAGE_CENTER, 1.0);
        int statsZone =
                GUI.addZone(
                        CHANNEL_GAME,
                        0.0,
                        TARGET_WINDOW_VERTICAL_ORIGIN,
                        0.0,
                        0.2,
                        DualityMode.SHORT_TEXT
                );
        int targetZone =
                GUI.addZone(
                        CHANNEL_GAME,
                        TARGET_WINDOW_VERTICAL_ORIGIN,
                        MESSAGE_WINDOW_VERTICAL_ORIGIN - TARGET_WINDOW_VERTICAL_ORIGIN,
                        0.0,
                        0.2,
                        DualityMode.SHORT_TEXT

                );
        int actionsZone =
                GUI.addZone(
                        CHANNEL_GAME,
                        0.0,
                        MESSAGE_WINDOW_VERTICAL_ORIGIN,
                        0.8,
                        0.2,
                        DualityMode.SHORT_TEXT
                );
        int fsText = GUI.addChannel(DualityMode.LONG_TEXT);
        //paranoia - these checks can be removed once we stop adding GUI channels/zones:
        if (ZONE_MESSAGE_CENTER != messageZone)
            throw new IllegalStateException("Invalid zone index for Message Center - expected " +
                    ZONE_MESSAGE_CENTER + " but was " + messageZone);
        if (ZONE_PLAYER_STATUS != statsZone)
            throw new IllegalStateException("Invalid zone index for Player Status - expected " +
                    ZONE_PLAYER_STATUS + " but was " + statsZone);
        if (ZONE_TARGET_STATUS != targetZone)
            throw new IllegalStateException("Invalid zone index for Target Status - expected " +
                    ZONE_TARGET_STATUS + " but was " + targetZone);
        if (ZONE_PLAYER_ACTIONS != actionsZone)
            throw new IllegalStateException("Invalid zone index for Player Actions - expected " +
                    ZONE_PLAYER_ACTIONS + " but was " + actionsZone);
        if (CHANNEL_FULLSCREEN_TEXT != fsText)
            throw new IllegalStateException("Invalid channel index for Fullscreen Text - expected " +
                    CHANNEL_FULLSCREEN_TEXT + " but was " + fsText);
        updateColorScheme();
        changeChannelToFullscreenText(); //ensure we start in text mode
    }
    public void updateColorScheme() {
        Glyph g = Session.getColorScheme().emptyGlyph();
        for (int c = 0; c < GUI.countChannels(); ++c) {
            GUI.setBackground(c, g);
            for (int z = 0; z < GUI.countZones(c); ++z) {
                GUI.setBackground(c, z, g);
            }
        }
        GUI.setBorder(CHANNEL_GAME, ZONE_MESSAGE_CENTER, DisplayStandards.getMessageWindowBorder());
        GUI.setBorder(CHANNEL_GAME, ZONE_PLAYER_STATUS, DisplayStandards.getPlayerStatsBorder());
        GUI.setBorder(CHANNEL_GAME, ZONE_TARGET_STATUS, DisplayStandards.getTargetStatsBorder());
        GUI.setBorder(CHANNEL_GAME, ZONE_PLAYER_ACTIONS, DisplayStandards.getPlayerActionsBorder());
    }

    public void changeChannelToGameDisplay() {
        GUI.changeChannel(CHANNEL_GAME);
        clearScreen();
    }
    public void changeChannelToFullscreenText() {
        GUI.changeChannel(CHANNEL_FULLSCREEN_TEXT);
        clearScreen();
    }

    public void clearScreen() {
        GUI.clear();
    }
    public void clearZone(int zone) {
        GUI.clear(zone);
    }
    public void printGlyph(int row, int column, Glyph g) {
        GUI.print(row, column, g);
    }
    public void printGlyph(int zone, int row, int column, Glyph g) {
        GUI.print(zone, row, column, g);
    }
    public Point printGlyphString(int row, int column, GlyphString gs) {
        return GUI.print(row, column, gs);
    }
    public Point printGlyphString(int zone, int row, int column, GlyphString gs) {
        return GUI.print(zone, row, column, gs);
    }

    public int maxCol() {
        return GUI.maxCol();
    }
    public int minCol() {
        return GUI.minCol();
    }
    public int maxRow() {
        return GUI.maxRow();
    }
    public int minRow() {
        return GUI.minRow();
    }
    public int maxCol(int zoneID) {
        return GUI.maxCol(zoneID);
    }
    public int minCol(int zoneID) {
        return GUI.minCol(zoneID);
    }
    public int maxRow(int zoneID) {
        return GUI.maxRow(zoneID);
    }
    public int minRow(int zoneID) {
        return GUI.minRow(zoneID);
    }
    public int from(int channelID, int zoneID, boolean row, boolean before) {
        return GUI.from(channelID, zoneID, row, before);
    }
    public void printCenteredBlock(double percentFromTop, String[] text) {
        int row = GUI.rowAtPercent(percentFromTop);
        for (int i = 0; i < text.length; ++i)
            printCenteredLine(
                    row++,
                    text[i]
            );
    }
    public void printCenteredLine(double percentFromTop, String text) {
        printCenteredLine(
                percentFromTop,
                text,
                Session.getColorScheme()
        );
    }
    public void printCenteredLine(double percentFromTop, String text, ChromaSet cs) {
        printCenteredLine(
                GUI.rowAtPercent(percentFromTop),
                text,
                cs
        );
    }
    public void printCenteredLine(int row, String text) {
        printCenteredLine(
                row,
                text,
                Session.getColorScheme()
        );
    }
    public void printCenteredLine(int row, String text, ChromaSet cs) {
        GUI.printCentered(
                row,
                new GlyphString(text,
                        cs.getBackground(),
                        cs.getForeground()
                )
        );
    }
    public void printHighlightedCenteredLine(int row, String text, ChromaSet cs) {
        GUI.printCentered(
                row,
                new GlyphString(text,
                        cs.getBackground(),
                        cs.getHighlight()
                )
        );
    }
    public void printMenu(double percentFromTop, Menu menu) {
        printMenu(GUI.rowAtPercent(percentFromTop), menu);
    }
    private void printMenu(int row, Menu menu) {
        GUI.printMenu(row, menu, Session.getColorScheme());
    }
    public void printMessages() {
        GUI.clear(ZONE_MESSAGE_CENTER);
        Message last = Session.getMessageCenter().getLastMessage();
        Message onScreen = Session.getMessageCenter().getOnScreenMessages();
        Point lastGlyph = GUI.print(
                ZONE_MESSAGE_CENTER,
                1,
                1,
                new GlyphString(last.getText(), last.getBackground(), last.getForeground())
        );
        GUI.print(
                ZONE_MESSAGE_CENTER,
                lastGlyph.y,
                lastGlyph.x,
                new GlyphString(onScreen.getText(), onScreen.getBackground(), onScreen.getForeground())
        );
    }
    public void printPlayerStatistics() {
        Player player = Session.getPlayer();
        Experience experience = player.getExperience();
        Equipment equipment = player.getEquipment();
        int playerLevel = experience.getLevel();
        long playerExperience = experience.getExp();
        double xpPercent = 100.0 * ((double)playerExperience / (double)Experience.calculateXP(playerLevel + 1));
        String xpString = Format.percent(
                xpPercent,
                0
        );
        double healthPercent = player.getActor().getCombatant().getHealthPercent();
        MeleeWeapon meleeWeapon = equipment.getWeaponsBelt().showWielded();
        Armor armor = equipment.getBodyArmor().showArmor();
        double weaponPercent = meleeWeapon.getDurabilityPercent();
        double armorPercent = armor.getDurabilityPercent();
        GUI.clear(ZONE_PLAYER_STATUS);
        int row = 0;
        Point last;
        GUI.print(
                ZONE_PLAYER_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Level: " + playerLevel,
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        GUI.print(
                ZONE_PLAYER_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Progress: " + xpString,
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        last = GUI.print(
                ZONE_PLAYER_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Health: ",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        GUI.print(
                ZONE_PLAYER_STATUS,
                last.y,
                last.x,
                Format.colorCode("", healthPercent, 0)
        );
        last = GUI.print(
                ZONE_PLAYER_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Weapon: ",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        GUI.print(
                ZONE_PLAYER_STATUS,
                last.y,
                last.x,
                Format.colorCode("", weaponPercent, 0)
        );
        last = GUI.print(
                ZONE_PLAYER_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Armor: ",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        GUI.print(
                ZONE_PLAYER_STATUS,
                last.y,
                last.x,
                Format.colorCode("", armorPercent, 0)
        );
    }
    public void printTargetStatistics() {
        TargetList tl = Session.getTargetList();
        if (tl == null) return;
        Actor target = tl.get();
        GUI.clear(ZONE_TARGET_STATUS);
        int row = 0;
        Point last;
        if (target == null || target == Session.getPlayer().getActor()) {
            GUI.print(
                    ZONE_TARGET_STATUS,
                    ++row,
                    1,
                    new GlyphString(
                            "<no target>",
                            Session.getColorScheme().getBackground(),
                            Session.getColorScheme().subdueForeground()
                    )
            );
            return;
        }
        double healthPercent = target.getCombatant().getHealthPercent();
        last = GUI.print(
                ZONE_TARGET_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Target: ",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        last = GUI.print(
                ZONE_TARGET_STATUS,
                last.y,
                last.x,
                target.getTemplate().getColoredName()
        );
        row = last.y;
        last = GUI.print(
                ZONE_TARGET_STATUS,
                ++row,
                1,
                new GlyphString(
                        "Health: ",
                        Session.getColorScheme().getBackground(),
                        Session.getColorScheme().getForeground()
                )
        );
        GUI.print(
                ZONE_TARGET_STATUS,
                last.y,
                last.x,
                Format.colorCode("", healthPercent, 0)
        );
    }
    public void printPlayerActions() {
        InputCommandList icl = Session.getModeManager().getCurrentMode().getInput();
        Point last = new Point();
        for (InputCommand ic : icl) {
            last = GUI.print(
                    ZONE_PLAYER_ACTIONS,
                    last.y+1,
                    1,
                    new GlyphString(
                            ic + ": ",
                            Session.getColorScheme().getBackground(),
                            Session.getColorScheme().getHighlight()
                    )
            );
            last = GUI.print(
                    ZONE_PLAYER_ACTIONS,
                    last.y,
                    last.x,
                    new GlyphString(
                            ic.getName(),
                            Session.getColorScheme().getBackground(),
                            Session.getColorScheme().getForeground()
                    )
            );
        }
    }

    public void toggleFullScreenMode() {
        GUI.toggleFullScreen();
    }

    Dimension getScreenDimension() {
        return new Dimension(GUI.countColumns(), GUI.countRows());
    }

    public int getMessageWindowRows() {
        return messageWindowRows;
    }

    public int getMessageWindowCols() {
        return messageWindowCols;
    }
}
