package world.dungeon.floor;

import util.Coordinate;
import world.actor.Actor;
import world.dungeon.theme.DungeonTheme;
import world.light.Light;

/**
 * Represents a game level map.
 */
public class Floor {

    public final int DEPTH;
    public final int ROWS;
    public final int COLS;
    FloorTile[][] floorTiles;
    private Coordinate playerSpawn;

    public Floor(int depth, DungeonTheme dt) {
        DEPTH = depth;
        ROWS = dt.randomizeRows();
        COLS = dt.randomizeCols();
        floorTiles = new FloorTile[ROWS][COLS];
        initializeTiles(dt.getAmbientLight());
    }
    private void initializeTiles(Light l) {
        floorTiles = new FloorTile[ROWS][COLS];
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                FloorTile ft = new FloorTile();
                ft.setLight(l);
                //todo - initialize other necessary fields as required
                floorTiles[i][j] = ft;
            }
        }
    }

    public int getSize() {
        return ROWS * COLS;
    }
    //exists in the floor
    public boolean inFloor(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
    //exists within the floor inside the outer edges
    public boolean isInteriorCoordinate(int row, int col) {
        return row > 0 && row < ROWS - 1 && col > 0 && col < COLS - 1;
    }
    public FloorTile tileAt(int row, int col) {
        return floorTiles[row][col];
    }
    public void placeActor(Actor actor, Coordinate coordinate) {
        int currentRow, currentCol;
        if (actor.getLocation() != null) {
            currentRow = actor.getLocation().getRow();
            currentCol = actor.getLocation().getColumn();
            if (inFloor(currentRow, currentCol)) {
                floorTiles[currentRow][currentCol].setActor(null);
            }
        }
        actor.setLocation(coordinate);
        currentRow = coordinate.getRow();
        currentCol = coordinate.getColumn();
        floorTiles[currentRow][currentCol].setActor(actor);
    }
    public Coordinate getPlayerSpawn() {
        return playerSpawn;
    }

    public void setPlayerSpawn(Coordinate playerSpawn) {
        this.playerSpawn = playerSpawn;
    }
}
