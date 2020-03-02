package world.dungeon.floor;

import io.out.message.MessageType;
import main.Session;
import util.Coordinate;
import world.actor.Actor;
import world.dungeon.theme.DungeonTheme;
import world.light.Light;
import world.terrain.TerrainTemplate;

import java.io.Serializable;

/**
 * Represents a game level map.
 */
public class Floor implements Serializable {

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
        return row > 1 && row < ROWS - 2 && col > 1 && col < COLS - 2;
    }
    public FloorTile tileAt(int row, int col) {
        return floorTiles[row][col];
    }
    public void placeActor(Actor actor, Coordinate coordinate) {
        int currentRow, currentCol;
        FloorTile floorTile;
        if (actor.getLocation() != null) {
            currentRow = actor.getLocation().getRow();
            currentCol = actor.getLocation().getColumn();
            if (inFloor(currentRow, currentCol)) {
                floorTile = floorTiles[currentRow][currentCol];
                floorTile.setActor(null);
            }
        }
        actor.setLocation(coordinate);
        currentRow = coordinate.getRow();
        currentCol = coordinate.getColumn();
        floorTile = floorTiles[currentRow][currentCol];
        floorTile.setActor(actor);
        if (actor == Session.getPlayer().getActor()) {
            TerrainTemplate tt = (TerrainTemplate)floorTile.getTerrain().getTemplate();
            if (tt.isMessageOnMove())
                Session.getMessageCenter().sendMessage(
                        "You see " + tt.getDescription() + " here."
                        , MessageType.INFO
                );
        }
    }
    public Coordinate getPlayerSpawn() {
        return playerSpawn;
    }

    public void setPlayerSpawn(Coordinate playerSpawn) {
        this.playerSpawn = playerSpawn;
    }
}
