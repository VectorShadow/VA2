package main;

import util.Coordinate;

import java.io.Serializable;

/**
 * Tracks the center of the screen for drawing purposes.
 */
public class Camera implements Serializable {
    private Coordinate at;
    private boolean trackPlayer = false;

    public Camera() {
        at = new Coordinate(0,0);
    }

    public Coordinate getAt() {
        return trackPlayer ? Session.getPlayer().getActor().getLocation() : at;
    }

    public void setAt(Coordinate at) {
        trackPlayer = false;
        this.at = at;
    }
    public void trackPlayer() {
        trackPlayer = true;
    }
}
