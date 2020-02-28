package util;

import main.Session;

public enum Direction {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0),
    SOUTH_WEST(1, -1),
    WEST(0, -1),
    NORTH_WEST(-1, -1),
    SELF(0, 0);

    private final int DR;
    private final int DC;

    Direction(int dr, int dc) {
        DR = dr;
        DC = dc;
    }

    public Coordinate shift(Coordinate from) {
        return new Coordinate(from.getRow() + DR, from.getColumn() + DC);
    }
    public boolean isDiagonal() {
        return this == NORTH_EAST || this == SOUTH_EAST || this == SOUTH_WEST || this == NORTH_WEST;
    }
    public Direction reverse() {
        switch (this) {
            case NORTH: return SOUTH;
            case NORTH_EAST: return SOUTH_WEST;
            case EAST: return WEST;
            case SOUTH_EAST: return NORTH_WEST;
            case SOUTH: return NORTH;
            case SOUTH_WEST: return NORTH_EAST;
            case WEST: return EAST;
            case NORTH_WEST: return SOUTH_EAST;
            case SELF: return SELF;
            default:
                throw new IllegalStateException();
        }
    }
    public Direction rotateClockwise() {
        switch (this) {
            case NORTH: return NORTH_EAST;
            case NORTH_EAST: return EAST;
            case EAST: return SOUTH_EAST;
            case SOUTH_EAST: return SOUTH;
            case SOUTH: return SOUTH_WEST;
            case SOUTH_WEST: return WEST;
            case WEST: return NORTH_WEST;
            case NORTH_WEST: return NORTH;
            default:
                throw new IllegalStateException("" + this);
        }
    }
    public Direction rotateCountClockwise() {
        switch (this) {
            case NORTH: return NORTH_WEST;
            case NORTH_EAST: return NORTH;
            case EAST: return NORTH_EAST;
            case SOUTH_EAST: return EAST;
            case SOUTH: return SOUTH_EAST;
            case SOUTH_WEST: return SOUTH;
            case WEST: return SOUTH_WEST;
            case NORTH_WEST: return WEST;
            default:
                throw new IllegalStateException("" + this);
        }
    }
    public static Direction random(boolean permitSelf) {
        Direction target = SELF;
        do {
            int r = Session.getRNG().nextInt(Direction.values().length);
            for (Direction direction : Direction.values()) {
                if (direction.ordinal() == r) target = direction;
            }
        } while (!permitSelf && target == SELF);
        return target;
    }
}
