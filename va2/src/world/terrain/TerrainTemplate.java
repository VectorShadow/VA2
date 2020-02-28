package world.terrain;

import world.WorldObjectTemplate;

import java.awt.*;
import java.util.ArrayList;

/**
 * Contains information required to describe and represent a generic type of terrain.
 */
public class TerrainTemplate extends WorldObjectTemplate {
    private final boolean permitLight;
    private final boolean permitMovement;
    public TerrainTemplate(
            int r,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            boolean permitLight,
            boolean permitMovement) {
        super(r, s, b, f);
        this.permitLight = permitLight;
        this.permitMovement = permitMovement;
    }

    public boolean permitsLight() {
        return permitLight;
    }

    public boolean permitsMovement() {
        return permitMovement;
    }
}
