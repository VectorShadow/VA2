package world.dungeon.theme;

import main.Session;
import resources.continuum.Continuum;
import world.dungeon.floor.Floor;
import world.dungeon.generate.FloorGenerator;
import world.light.Light;


/**
 * Contains all necessary information to generate a thematic dungeon.
 */
public class DungeonTheme {

    private final Light AMBIENT_LIGHT;
    private final int MAXIMUM_ROWS;
    private final int MAXIMUM_COLS;
    private final int ROW_VARIANCE;
    private final int COL_VARIANCE;

    private final Continuum<FloorGenerator> GENERATORS;
    //todo - roomSet, which includes a list of room patterns and a mapping of chars to terrainTemplates
    //todo - roomPatternDefinitions, which contains all defined room patterns to be referenced by roomSets

    DungeonTheme(Light light, int maxRows, int maxCols, int rowVar, int colVar, Continuum<FloorGenerator> floorGen) {
        if (maxRows - 2 * rowVar < 0 || maxCols - 2 * colVar < 0)
            throw new IllegalArgumentException("Size parameters out of range.");
        AMBIENT_LIGHT = light;
        MAXIMUM_ROWS = maxRows;
        MAXIMUM_COLS = maxCols;
        ROW_VARIANCE = rowVar;
        COL_VARIANCE = colVar;
        GENERATORS = floorGen;
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
    private FloorGenerator randomizeFloorGenerator() {
        return GENERATORS.getValue(Session.getRNG());
    }
    public Floor generateFloor(int depth) {
        return randomizeFloorGenerator().generate(depth, this);
    }
}
