package world.dungeon.generate;

import main.Session;
import util.Coordinate;
import util.Direction;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import world.dungeon.theme.DungeonTheme;
import world.terrain.Terrain;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a floor that consists of randomized clearings with meandering paths in between them.
 */
public class WarrenGenerator extends FloorGenerator{

    TerrainTemplate wallTemplate;
    TerrainTemplate floorTemplate;
    TerrainTemplate crossTemplate;

    @Override
    public Floor generate(DungeonTheme dungeonTheme, int floorDepth) {
        floor = new Floor(dungeonTheme);
        Random r = Session.getRNG();
        //todo - derive these from the DungeonTheme:
        wallTemplate = TerrainDefinitions.ANCIENT_OAK;
        floorTemplate = TerrainDefinitions.GRASSY_FLOOR;
        crossTemplate = TerrainDefinitions.SHALLOW_WATER;
        fill(wallTemplate);
        int size = floor.getSize();
        //calculate the number of clearings to generate based on the size of the floor
        int clearingCount = size / 64 - r.nextInt(size / 256);
        ArrayList<Coordinate> clearingCenters = new ArrayList<>();
        for (int i = 0; i < clearingCount; ++i) {
            //randomly generate coordinates away from the edges of the floor
            int randomRow = r.nextInt((int)(floor.getRows() * 0.85)) + (int)(floor.getRows() * 0.075);
            int randomCol = r.nextInt((int)(floor.getCols() * 0.85)) + (int)(floor.getCols() * 0.075);
            Coordinate c = new Coordinate(randomRow, randomCol);
            if (!clearingCenters.contains(c)) //don't duplicate random coordinates by accident
                clearingCenters.add(c);
            else
                --i;
        }
        //find the two most widely separated clearing centers
        CoordinatePair mostDistantClearings = mostDistantPair(clearingCenters);
        //pick one to start from
        Coordinate currentClearingCenter = mostDistantClearings.getC1();
        do {
            //remove the current clearing from the set of clearings remaining
            clearingCenters.remove(currentClearingCenter);
            //open a clearing around the current center
            openClearing(currentClearingCenter);
            //find the closest clearing to this
            Coordinate nextClearingCenter = leastDistant(currentClearingCenter, clearingCenters);
            //open a path from this clearing to the next
            meanderingPath(currentClearingCenter, nextClearingCenter);
            //designate the next clearing as the current clearing
            currentClearingCenter = nextClearingCenter;
        } while (clearingCenters.size() > 1);
        //open up the final clearing
        openClearing(currentClearingCenter);
        crossTerrain();
        placeEntryStair(mostDistantClearings.getC1());
        placeEndStairs(mostDistantClearings.getC2());
        return floor;
    }

    private void openClearing(Coordinate c) {
        FloorTile ft = floor.tileAt(c.getRow(), c.getColumn());
        ft.setTerrain(new Terrain(floorTemplate));
        for (int i = 0; i < 16; ++i) { //make multiple attempts at radiating
            Coordinate at = c;
            Coordinate next;
            for (int j = 0; j < 7; ++j) { //attempt to progress this ray path multiple times
                next = Direction.random(false).shift(at); //shift randomly
                if (!floor.isInteriorCoordinate(next.getRow(), next.getColumn())) continue; //don't clear floor edges
                at = next;
                ft = floor.tileAt(at.getRow(), at.getColumn());
                ft.setTerrain(new Terrain(floorTemplate));
            }
        }
    }
    private void meanderingPath(Coordinate c1, Coordinate c2) {
        FloorTile ft = floor.tileAt(c1.getRow(), c1.getColumn());
        ft.setTerrain(new Terrain(floorTemplate));
        Coordinate at = c1;
        Coordinate next;
        do {
            do {
                Direction meander = Direction.random(false); //pick a random direction
                next = meander.shift(at);
                //try to move closer to the target, but occasionally allow deviation - just not off the edge
            } while (!floor.isInteriorCoordinate(next.getRow(), next.getColumn()) ||
                    (next.distanceTo(c2) >= at.distanceTo(c2) || Session.getRNG().nextDouble() < 0.1));
            at = next;
            ft = floor.tileAt(at.getRow(), at.getColumn());
            ft.setTerrain(new Terrain(floorTemplate));
        } while (!at.equals(c2));
    }
    private int nearEdge(int dimension) {
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
    private void crossTerrain() {
        Random r = Session.getRNG();
        FloorTile ft;
        for (int i = 0; i < r.nextInt(3); ++i) {
            Coordinate origin, target;
            do {
                origin = new Coordinate(nearEdge(floor.getRows()), nearEdge(floor.getCols()));
                target = new Coordinate(nearEdge(floor.getRows()), nearEdge(floor.getCols()));
            } while (origin.distanceTo(target) > Math.sqrt(floor.getSize()));
            Coordinate at = origin;
            Coordinate next;
            do {
                do {
                    Direction meander = Direction.random(false); //pick a random direction
                    next = meander.shift(at);
                    //try to move closer to the target, but occasionally allow deviation - just not off the edge
                } while (!floor.isInteriorCoordinate(next.getRow(), next.getColumn()) ||
                        (next.distanceTo(target) >= at.distanceTo(target) || Session.getRNG().nextDouble() < 0.2));
                at = next;
                ft = floor.tileAt(at.getRow(), at.getColumn());
                ft.setTerrain(new Terrain(crossTemplate));
                next = Direction.random(false).shift(at);
                //also add terrain to a random adjacent tile, to increase total size
                ft = floor.tileAt(next.getRow(), next.getColumn());
                ft.setTerrain(new Terrain(crossTemplate));
            } while (!at.equals(target));
        }
    }

}
