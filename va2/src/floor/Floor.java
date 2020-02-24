package floor;

import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;

/**
 * Represents a game level map.
 */
public class Floor {
    final int ROWS;
    final int COLS;
    FloorTile[][] floorTiles;

    public Floor(int rows, int cols) {
        ROWS = rows;
        COLS = cols;
        floorTiles = new FloorTile[ROWS][COLS];
        //hack - test display - todo - initialize with EMPTY:
        for (FloorTile[] ft : floorTiles) {
            for (FloorTile f : ft) {
                f.setTerrain(new Terrain(TerrainDefinitions.SIMPLE_FLOOR));
            }
        }
    }
}
