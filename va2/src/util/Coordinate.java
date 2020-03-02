package util;

import java.io.Serializable;

/**
 * A pair of integers specifying row and column. Used for accessing tiles within a world.dungeon.floor.
 */
public class Coordinate implements Serializable {
    private final int ROW;
    private final int COL;

    public Coordinate(int r, int c) {
        ROW = r;
        COL = c;
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COL;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate && ((Coordinate) obj).ROW == ROW && ((Coordinate) obj).COL == COL;
    }

    @Override
    public String toString() {
        return "(" + ROW + "," + COL + ")";
    }

    public double distanceTo(Coordinate c) {
        int dr = Math.abs(c.ROW - ROW);
        int dc = Math.abs(c.COL - COL);
        return Math.sqrt((dr * dr) + (dc * dc));
    }
}
