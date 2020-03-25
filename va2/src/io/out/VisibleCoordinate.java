package io.out;

import util.Coordinate;

/**
 * An object which pairs a coordinate with a double distance, for tracking sight and light.
 */
public class VisibleCoordinate extends Coordinate {
    private final double distance;

    public VisibleCoordinate(int r, int c, double d) {
        super(r, c);
        distance = d;
    }
    public VisibleCoordinate(Coordinate c, double d) {
        this(c.getRow(), c.getColumn(), d);
    }

    double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return super.toString() + "@" + distance;
    }
}
