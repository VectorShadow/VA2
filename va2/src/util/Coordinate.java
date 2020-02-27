package util;

public class Coordinate {
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
}
