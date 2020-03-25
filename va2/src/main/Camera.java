package main;

import util.Coordinate;
import util.Direction;
import world.dungeon.floor.Floor;

import java.io.Serializable;

/**
 * Tracks the center of the screen for drawing purposes.
 */
public class Camera implements Serializable {
    private static final int SHIFT_DISTANCE = 8;

    private Coordinate at;
    private boolean trackPlayer = false;

    public Camera() {
        at = new Coordinate(0,0);
    }

    public Coordinate at() {
        return trackPlayer ? playerAt() : at;
    }

    public void shift(Direction d) {
        Floor f = Session.getCurrentFloor();
        Coordinate next = at;
        for (int i = 0; i < SHIFT_DISTANCE; ++i) {
            next = d.shift(next);
            if (f.inFloor(next.getRow(), next.getColumn()))
                setAt(next);
            else
                break;
        }
    }

    private Coordinate playerAt() {
        return Session.getPlayer().getActor().getLocation();
    }

    private void setAt(Coordinate at) {
        trackPlayer = false;
        this.at = at;
    }
    public void trackPlayer() {
        trackPlayer = true;
        at = playerAt(); //re-center on the player
    }
}
