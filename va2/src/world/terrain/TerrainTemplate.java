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
    private final boolean messageOnMove;
    public TerrainTemplate(
            String n,
            String d,
            boolean r,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            boolean permitLight,
            boolean permitMovement,
            boolean messageOnMove) {
        super(n, d, r, s, b, f);
        this.permitLight = permitLight;
        this.permitMovement = permitMovement;
        this.messageOnMove = messageOnMove;
    }

    public boolean permitsLight() {
        return permitLight;
    }

    public boolean permitsMovement() {
        return permitMovement;
    }

    public boolean isMessageOnMove() {
        return messageOnMove;
    }
}
