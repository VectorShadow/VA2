package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import resources.glyph.GlyphString;
import world.dungeon.generate.FloorGenerator;
import world.light.Light;

import java.io.Serializable;


/**
 * Contains all necessary information to generate a thematic dungeon.
 */
public class DungeonTheme implements Serializable {

    private final Light AMBIENT_LIGHT;
    private final int MAXIMUM_ROWS;
    private final int MAXIMUM_COLS;
    private final int MAXIMUM_ENEMIES;
    private final int ROW_VARIANCE;
    private final int COL_VARIANCE;
    private final int ENEMY_VARIANCE;
    private final int DUNGEON_DEPTH;
    private final GlyphString DUNGEON_NAME;

    private final String[] FINAL_FLOOR_ACTORS;
    private final String[] FINAL_FLOOR_MAP;

    private final Continuum<FloorGenerator> GENERATORS;
    private final ActorSet ACTOR_SET;
    private final TerrainSet TERRAIN_SET;
    //todo - roomSet, which includes a list of room patterns and a mapping of chars to terrainTemplates
    //todo - roomPatternDefinitions, which contains all defined room patterns to be referenced by roomSets
    //todo - boss floor room and actor definitons

    DungeonTheme(
            Light light,
            int maxRows,
            int maxCols,
            int maxEnemies,
            int rowVar,
            int colVar,
            int enemyVar,
            int depth,
            GlyphString name,
            String[] actors,
            String[] map,
            Continuum<FloorGenerator> floorGen,
            ActorSet actorSet,
            TerrainSet terrainSet
    ) {
        if (maxRows - 2 * rowVar < 0 || maxCols - 2 * colVar < 0)
            throw new IllegalArgumentException("Size parameters out of range.");
        AMBIENT_LIGHT = light;
        MAXIMUM_ROWS = maxRows;
        MAXIMUM_COLS = maxCols;
        MAXIMUM_ENEMIES = maxEnemies;
        ROW_VARIANCE = rowVar;
        COL_VARIANCE = colVar;
        ENEMY_VARIANCE = enemyVar;
        DUNGEON_DEPTH = depth;
        DUNGEON_NAME = name;
        FINAL_FLOOR_ACTORS = actors;
        FINAL_FLOOR_MAP = map;
        GENERATORS = floorGen;
        ACTOR_SET = actorSet;
        TERRAIN_SET = terrainSet;
    }

    public Light getAmbientLight() {
        return AMBIENT_LIGHT;
    }
    public int randomizeRows() {
        return MAXIMUM_ROWS - Session.getRNG().nextInt(ROW_VARIANCE);
    }
    public int randomizeCols() {
        return MAXIMUM_COLS - Session.getRNG().nextInt(COL_VARIANCE);
    }
    public int finalFloorRows() {
        return FINAL_FLOOR_MAP.length;
    }
    public int finalFloorCols() {
        return FINAL_FLOOR_MAP[0].length();
    }
    public int randomizeEnemies() {
        return MAXIMUM_ENEMIES - Session.getRNG().nextInt(ENEMY_VARIANCE);
    }
    public FloorGenerator randomizeFloorGenerator() {
        return GENERATORS.getValue(Session.getRNG());
    }

    public String[] getFinalFloorActors() {
        return FINAL_FLOOR_ACTORS;
    }

    public String[] getFinalFloorMap() {
        return FINAL_FLOOR_MAP;
    }

    public ActorSet getActorSet() {
        return ACTOR_SET;
    }
    public TerrainSet getTerrainSet() {
        return TERRAIN_SET;
    }

    public int getDepth() {
        return DUNGEON_DEPTH;
    }

    public GlyphString getName() {
        return DUNGEON_NAME;
    }
}
