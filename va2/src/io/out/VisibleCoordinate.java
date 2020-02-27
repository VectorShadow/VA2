package io.out;

import util.Coordinate;

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
