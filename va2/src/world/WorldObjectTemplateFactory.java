package world;

import ai.AI;
import combat.Combatant;
import main.progression.rewards.Loot;
import main.progression.rewards.Reward;
import resources.glyph.BalancedGlyphTemplate;
import world.actor.ActorTemplate;
import world.item.ItemTemplate;
import world.item.material.Material;
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

    private String name = "";
    private String description = "";

    private ArrayList<Character> symbols;
    private ArrayList<Color> backgroundColors;
    private ArrayList<Color> foregroundColors;
    private ArrayList<Color> secondaryColors;
    private ArrayList<Color> tertiaryColors;

    private int imageRow = -1;
    private int imageCol = -1;

    private boolean reflectLight = false;

    private WorldObjectTemplateFactory(){
        symbols = new ArrayList<>();
        backgroundColors = new ArrayList<>();
        foregroundColors = new ArrayList<>();
        secondaryColors = new ArrayList<>();
        tertiaryColors = new ArrayList<>();
    }

    public static WorldObjectTemplateFactory initialize() {
        return new WorldObjectTemplateFactory();
    }
    public static WorldObjectTemplateFactory initialize(boolean reflectLight) {
        WorldObjectTemplateFactory wotf = initialize();
        wotf.reflectLight = reflectLight;
        return wotf;
    }
    public WorldObjectTemplateFactory setName(String n) {
        name = n;
        if (description.equals(""))
            description = name;
        return this;
    }
    public WorldObjectTemplateFactory setDescription(String d) {
        description = d;
        return this;
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
    public WorldObjectTemplateFactory setSecondaryColors(Color c) {
        secondaryColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setSecondaryColors(Color[] colors) {
        for (Color c : colors) secondaryColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setSecondaryColors(ArrayList<Color> colors) {
        secondaryColors = colors;
        return this;
    }
    public WorldObjectTemplateFactory setTertiaryColors(Color c) {
        tertiaryColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setTertiaryColors(Color[] colors) {
        for (Color c : colors) tertiaryColors.add(c);
        return this;
    }
    public WorldObjectTemplateFactory setTertiaryColors(ArrayList<Color> colors) {
        tertiaryColors = colors;
        return this;
    }
    public WorldObjectTemplateFactory setImage(int iRow, int iCol) {
        imageRow = iRow;
        imageCol = iCol;
        return this;
    }
    private void forceNonEmptyLists() {
        if (symbols.isEmpty()) symbols.add(DEFAULT_SYMBOL);
        if (backgroundColors.isEmpty()) backgroundColors.add(DEFAULT_BACKGROUND_COLOR);
        if (foregroundColors.isEmpty()) foregroundColors.add(DEFAULT_FOREGROUND_COLOR);
        if (secondaryColors.isEmpty()) secondaryColors.add(DEFAULT_FOREGROUND_COLOR);
        if (tertiaryColors.isEmpty()) tertiaryColors.add(DEFAULT_FOREGROUND_COLOR);
    }
    public ActorTemplate manufactureActorTemplate(
            int energyPerTurn,
            Combatant combatant,
            int rewardExperience,
            AI ai
    ) {
        return manufactureActorTemplate(energyPerTurn, 1, combatant,  rewardExperience, Loot.ALL_FAMILIES, ai);
    }
    public ActorTemplate manufactureActorTemplate(
            int energyPerTurn,
            int minimumDepth,
            Combatant combatant,
            int rewardExperience,
            AI ai
    ) {
        return manufactureActorTemplate(energyPerTurn, minimumDepth, combatant, rewardExperience, Loot.ALL_FAMILIES, ai);
    }
    public ActorTemplate manufactureActorTemplate(
            int energyPerTurn,
            Combatant combatant,
            int rewardExperience,
            int rewardItemFamily,
            AI ai
    ) {
        return manufactureActorTemplate(energyPerTurn, 1, combatant, rewardExperience, rewardItemFamily, ai);
    }
    public ActorTemplate manufactureActorTemplate(
            int energyPerTurn,
            int minimumDepth,
            Combatant combatant,
            int rewardExperience,
            int rewardItemFamily,
            AI ai
    ) {
        forceNonEmptyLists();
        return new ActorTemplate(
                description,
                name,
                new BalancedGlyphTemplate(
                        symbols,
                        backgroundColors,
                        foregroundColors,
                        secondaryColors,
                        tertiaryColors,
                        imageRow,
                        imageCol
                ),
                reflectLight,
                energyPerTurn,
                minimumDepth,
                combatant.clone(),
                rewardExperience,
                rewardItemFamily,
                ai
        );
    }
    public ItemTemplate manufactureItemTemplate(int durability, Material material, int itemID) {
        forceNonEmptyLists();
        return new ItemTemplate(
                description,
                name,
                new BalancedGlyphTemplate(
                        symbols,
                        backgroundColors,
                        foregroundColors,
                        secondaryColors,
                        tertiaryColors,
                        imageRow,
                        imageCol
                ),
                durability,
                material,
                itemID
        );
    }
    public TerrainTemplate manufactureTerrainTemplate(boolean permitLight, boolean permitMovement, boolean messageOnMove) {
        forceNonEmptyLists();
        return new TerrainTemplate(
                description,
                name,
                new BalancedGlyphTemplate(
                        symbols,
                        backgroundColors,
                        foregroundColors,
                        secondaryColors,
                        tertiaryColors,
                        imageRow,
                        imageCol
                ),
                reflectLight,
                permitLight,
                permitMovement,
                messageOnMove
        );
    }
}
