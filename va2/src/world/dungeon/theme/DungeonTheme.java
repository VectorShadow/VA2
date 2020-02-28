package world.dungeon.theme;

import main.Session;
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

    DungeonTheme(Light light, int maxRows, int maxCols, int rowVar, int colVar) {
        if (maxRows - 2 * rowVar < 0 || maxCols - 2 * colVar < 0)
            throw new IllegalArgumentException("Size parameters out of range.");
        AMBIENT_LIGHT = light;
        MAXIMUM_ROWS = maxRows;
        MAXIMUM_COLS = maxCols;
        ROW_VARIANCE = rowVar;
        COL_VARIANCE = colVar;
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
}
