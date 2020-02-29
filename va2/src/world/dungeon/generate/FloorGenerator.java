package world.dungeon.generate;

import main.Session;
import util.Coordinate;
import util.Direction;
import world.dungeon.floor.Floor;
import world.dungeon.theme.DungeonTheme;
import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * The base class for Floor Generators.
 */
public abstract class FloorGenerator {

    protected Floor floor;
    protected int floorDepth;

    protected class CoordinatePair {
        private final Coordinate c1;
        private final Coordinate c2;

        public CoordinatePair(Coordinate a, Coordinate b) {
            c1 = a;
            c2 = b;
        }

        public Coordinate getC1() {
            return c1;
        }

        public Coordinate getC2() {
            return c2;
        }
    }
    public abstract Floor generate(int depth, DungeonTheme dungeonTheme);

    //fill the floor with terrain based on the provided template
    protected void fill(TerrainTemplate tt) {
        for (int i = 0; i < floor.ROWS; ++i) {
            for (int j = 0; j < floor.COLS; ++j) {
                floor.tileAt(i, j).setTerrain(new Terrain(tt));
            }
        }
    }

    private CoordinatePair evaluateDistance(ArrayList<Coordinate> cList, boolean maximize) {
        if (cList.size() < 2) throw new IllegalArgumentException("Not enough coordinates.");
        if (cList.size() < 3) return new CoordinatePair(cList.get(0), cList.get(1));
        Coordinate best1 = cList.get(0);
        Coordinate best2 = cList.get(1);
        double bestDistance = best1.distanceTo(best2);
        for (Coordinate c1 : cList) {
            for (Coordinate c2 : cList) {
                if (c1 == c2) continue;
                double d = c1.distanceTo(c2);
                if (maximize ? d > bestDistance : d < bestDistance) {
                    best1 = c1;
                    best2 = c2;
                    bestDistance = d;
                }
            }
        }
        return new CoordinatePair(best1, best2);
    }
    private Coordinate evaluateDistance(Coordinate c, ArrayList<Coordinate> cList, boolean maximize) {
        if (cList.size() < 1) throw new IllegalArgumentException("Not enough coordinates.");
        if (cList.size() < 2) return cList.get(0);
        Coordinate best = cList.get(0);
        double bestDistance = c.distanceTo(best);
        for (Coordinate c1 : cList) {
            if (c.equals(c1)) throw new IllegalArgumentException("Coordinate " + c + " contained in list.");
            double d = c.distanceTo(c1);
            if (maximize ? d > bestDistance : d < bestDistance) {
                best = c1;
                bestDistance = d;
            }
        }
        return best;
    }
    private int nearEdge(boolean byRow) {
        int dimension = byRow ? floor.ROWS : floor.COLS;
        Random r = Session.getRNG();
        int i;
        do {
            i = r.nextInt(dimension);
        } while (
                (i > 0.06 * dimension && i > 0 && i < 0.14 * dimension) ||
                        (i > 0.86 * dimension && i < 0.94 * dimension && i < dimension - 1)
        );
        return i;
    }
    protected Coordinate nearEdge() {
        return new Coordinate(nearEdge(true), nearEdge(false));
    }
    protected CoordinatePair mostDistantPair(ArrayList<Coordinate> cList) {
        return evaluateDistance(cList, true);
    }
    protected CoordinatePair leastDistantPair(ArrayList<Coordinate> cList) {
        return evaluateDistance(cList, false);
    }
    protected Coordinate mostDistant(Coordinate c, ArrayList<Coordinate> cList) {
        return evaluateDistance(c, cList, true);
    }
    protected Coordinate leastDistant(Coordinate c, ArrayList<Coordinate> cList) {
        return evaluateDistance(c, cList, false);
    }
    protected void placeEntryStair(Coordinate c) {
        floor.tileAt(c.getRow(), c.getColumn()).setTerrain(new Terrain(TerrainDefinitions.FLIGHT_STAIR));
        floor.setPlayerSpawn(c);
    }
    protected void placeEndStairs(Coordinate c) {
        floor.tileAt(c.getRow(), c.getColumn()).setTerrain(new Terrain(TerrainDefinitions.NEXT_FLOOR_STAIR));
        Coordinate c1 = Direction.random(false).shift(c);
        floor.tileAt(c1.getRow(), c1.getColumn()).setTerrain(new Terrain(TerrainDefinitions.REWARD_STAIR));
    }
}
