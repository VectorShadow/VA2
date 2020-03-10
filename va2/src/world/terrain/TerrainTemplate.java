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
            String d,
            String n,
            Color nc,
            ArrayList<Character> s,
            ArrayList<Color> b,
            ArrayList<Color> f,
            boolean r,
            boolean perLight,
            boolean perMove,
            boolean msgMove
    ) {
        super(d, n, nc, s, b, f, r);
        permitLight = perLight;
        permitMovement = perMove;
        messageOnMove = msgMove;
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
