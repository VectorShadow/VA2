package floor;

import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.actor.ActorDefinitions;
import world.light.Light;
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
        initializeTiles();
        //hack - test display
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                FloorTile ft = floorTiles[i][j];
                ft.setLight(Light.BIOLUM);
                if (i == 0 || i == ROWS - 1 || j == 0 || j == COLS - 1)
                    ft.setTerrain(new Terrain(TerrainDefinitions.SIMPLE_WALL));
                else if (i % 3 == 0 || j % 5 == 0)
                    ft.setTerrain(new Terrain(TerrainDefinitions.GRASSY_FLOOR));
                else
                    ft.setTerrain(new Terrain(TerrainDefinitions.SIMPLE_FLOOR));
            }
        }
    }
    private void initializeTiles() {
        floorTiles = new FloorTile[ROWS][COLS];
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                FloorTile ft = new FloorTile();
                ft.setTerrain(new Terrain(TerrainDefinitions.EMPTY));
                //todo - initialize other necessary fields as required
                floorTiles[i][j] = ft;
            }
        }
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }
    public boolean inFloor(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
    public FloorTile tileAt(int row, int col) {
        return floorTiles[row][col];
    }
    public void placeActor(Actor actor, Coordinate coordinate) {
        Coordinate currentLocation = actor.getLocation();
        int currentRow, currentCol;
        if (currentLocation != null) {
            currentRow = currentLocation.getRow();
            currentCol = currentLocation.getColumn();
            if (inFloor(currentRow, currentCol)) {
                floorTiles[currentRow][currentCol].setActor(null);
            }
        }
        actor.setLocation(coordinate);
        currentRow = coordinate.getRow();
        currentCol = coordinate.getColumn();
        floorTiles[currentRow][currentCol].setActor(actor);
    }
}
