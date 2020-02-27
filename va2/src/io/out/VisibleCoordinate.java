package io.out;

import util.Coordinate;

/**
 * An object which pairs a coordinate with a double distance, for tracking sight and light.
 */
public class VisibleCoordinate {
    private final Coordinate coordinate;
    private final double distance;

    VisibleCoordinate(Coordinate c, double d) {
        coordinate = c;
        distance = d;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return coordinate + "@" + distance;
    }
}
