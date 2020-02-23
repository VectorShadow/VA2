package floor;

import world.terrain.Terrain;

/**
 * Building blocks for a floor. Contains a flag to indicate whether the player has ever seen it,
 * as well as pointers to other world objects at its location.
 */
public class FloorTile {
    private boolean seen = false;
    private Terrain terrain;


    public boolean isSeen() {
        return seen;
    }
    public void setSeen(boolean b){
        seen = b;
    }

    public Terrain getTerrain() {
        return terrain;
    }
    public void setTerrain(Terrain t) {
        terrain = t;
    }

}
