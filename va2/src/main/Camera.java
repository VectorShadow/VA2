package main;

import util.Coordinate;

/**
 * Tracks the center of the screen for drawing purposes.
 */
public class Camera {
    private Coordinate at;
    private boolean trackPlayer = true;

    public Camera() {
        at = new Coordinate(0,0);
    }

    public Coordinate getAt() {
        return trackPlayer ? Session.getPlayerActor().getLocation() : at;
    }

    public void setAt(Coordinate at) {
        trackPlayer = false;
        this.at = at;
    }
    public void trackPlayer() {
        trackPlayer = true;
    }
}
