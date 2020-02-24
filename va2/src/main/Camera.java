package main;

import floor.Coordinate;

/**
 * Tracks the center of the screen for drawing purposes.
 */
public class Camera {
    private Coordinate at;

    public Camera() {
        at = new Coordinate(0,0);
    }

    public Coordinate getAt() {
        return at;
    }

    public void setAt(Coordinate at) {
        this.at = at;
    }
}
