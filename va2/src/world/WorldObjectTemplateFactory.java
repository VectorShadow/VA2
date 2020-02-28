package world;

import world.actor.ActorTemplate;
import world.terrain.TerrainTemplate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Generates Templates for all types of WorldObjects on demand.
 */
public class WorldObjectTemplateFactory {

    private static final char DEFAULT_SYMBOL = ' ';
    private static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;
    private static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;

    private int reflectThreshold = 255;

    private ArrayList<Character> symbols;
    private ArrayList<Color> backgroundColors;
    private ArrayList<Color> foregroundColors;

    private WorldObjectTemplateFactory(){
        symbols = new ArrayList<>();
        backgroundColors = new ArrayList<>();
        foregroundColors = new ArrayList<>();
    }

    public static WorldObjectTemplateFactory initialize() {
        return new WorldObjectTemplateFactory();
    }
    public static WorldObjectTemplateFactory initialize(int reflectThreshold) {
        WorldObjectTemplateFactory wotf = initialize();
        wotf.reflectThreshold = reflectThreshold;
        return wotf;
    }
    public WorldObjectTemplateFactory setSymbols(char c) {
        symbols.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setSymbols(char[] chars) {
        for (char c : chars) symbols.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setSymbols(ArrayList<Character> chars) {
        symbols = chars;
        return this;
    }
    public WorldObjectTemplateFactory setBackgroundColors(Color c) {
        backgroundColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setBackgroundColors(Color[] colors) {
        for (Color c : colors) backgroundColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setBackgroundColors(ArrayList<Color> colors) {
        backgroundColors = colors;
        return this;
    }
    public WorldObjectTemplateFactory setForegroundColors(Color c) {
        foregroundColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setForegroundColors(Color[] colors) {
        for (Color c : colors) foregroundColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setForegroundColors(ArrayList<Color> colors) {
        foregroundColors = colors;
        return this;
    }
    private void forceNonEmptyLists() {
        if (symbols.isEmpty()) symbols.add(DEFAULT_SYMBOL);
        if (backgroundColors.isEmpty()) backgroundColors.add(DEFAULT_BACKGROUND_COLOR);
        if (foregroundColors.isEmpty()) foregroundColors.add(DEFAULT_FOREGROUND_COLOR);
    }
    public ActorTemplate manufactureActorTemplate(int energyPerTurn) {
        forceNonEmptyLists();
        return new ActorTemplate(reflectThreshold, symbols, backgroundColors, foregroundColors, energyPerTurn);
    }
    public TerrainTemplate manufactureTerrainTemplate(boolean permitLight, boolean permitMovement) {
        forceNonEmptyLists();
        return new TerrainTemplate(reflectThreshold, symbols, backgroundColors, foregroundColors, permitLight, permitMovement);
    }
}
