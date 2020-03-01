package world.dungeon.generate;

import main.Session;
import util.Coordinate;
import util.Direction;
import world.dungeon.floor.Floor;
import world.dungeon.floor.FloorTile;
import world.dungeon.theme.DungeonTheme;
import world.dungeon.theme.TerrainSet;
import world.terrain.Terrain;
import world.terrain.TerrainTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Generates a floor that consists of randomized clearings with meandering paths in between them.
 */
public class WarrenGenerator extends FloorGenerator{

    @Override
    public Floor generate(int depth, DungeonTheme theme) {
        dungeonTheme = theme;
        floorDepth = depth;
        floor = new Floor(floorDepth, dungeonTheme);
        Random r = Session.getRNG();
        fill(dungeonTheme.getTerrainSet(), false);
        int size = floor.getSize();
        //calculate the number of clearings to generate based on the size of the floor
        int clearingCount = size / 64 - r.nextInt(size / 256);
        ArrayList<Coordinate> clearingCenters = new ArrayList<>();
        for (int i = 0; i < clearingCount; ++i) {
            //randomly generate coordinates away from the edges of the floor
            int randomRow = r.nextInt((int)(floor.ROWS * 0.85)) + (int)(floor.ROWS * 0.075);
            int randomCol = r.nextInt((int)(floor.COLS * 0.85)) + (int)(floor.COLS * 0.075);
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
        addStreams();
        placeEntryStair(mostDistantClearings.getC1());
        placeEndStairs(currentClearingCenter);
        return floor;
    }

    private void openClearing(Coordinate c) {
        TerrainSet ts = dungeonTheme.getTerrainSet();
        FloorTile ft = floor.tileAt(c.getRow(), c.getColumn());
        //use the base floor at the center
        ft.setTerrain(new Terrain(ts.getBasePrimaryFloor()));
        for (int i = 0; i < 16; ++i) { //make multiple attempts at radiating
            Coordinate at = c;
            Coordinate next;
            for (int j = 0; j < 5; ++j) { //attempt to progress this ray path multiple times
                next = Direction.random(false).shift(at); //shift randomly
                if (!floor.isInteriorCoordinate(next.getRow(), next.getColumn())) continue; //don't clear floor edges
                at = next;
                ft = floor.tileAt(at.getRow(), at.getColumn());
                //randomize the floor throughout the rest of the clearing, if applicable
                ft.setTerrain(new Terrain(dungeonTheme.getTerrainSet().getRandomPrimaryFloor()));
            }
        }
    }
    private void meanderingPath(Coordinate c1, Coordinate c2) {
        FloorTile ft = floor.tileAt(c1.getRow(), c1.getColumn());
        //use the base floor for all paths between clearings
        TerrainTemplate pathTemplate = dungeonTheme.getTerrainSet().getBasePrimaryFloor();
        ft.setTerrain(new Terrain(pathTemplate));
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
            ft.setTerrain(new Terrain(pathTemplate));
        } while (!at.equals(c2));
    }
    private void addStreams() {
        Random r = Session.getRNG();
        //randomly select one of the possible alternate floors to build the streams
        TerrainTemplate streamTemplate = dungeonTheme.getTerrainSet().getRandomAlternateFloor();
        FloorTile ft;
        for (int i = 0; i < r.nextInt(4); ++i) {
            Coordinate origin, target;
            do {
                origin = nearEdge();
                target = nearEdge();
            } while (origin.distanceTo(target) < 0.66 * Math.sqrt(floor.getSize()));
            Coordinate at = origin;
            Coordinate next;
            do {
                do {
                    Direction meander = Direction.random(false); //pick a random direction
                    next = meander.shift(at);
                    //try to move closer to the target, but occasionally allow deviation - just not off the edge
                } while (!floor.isInteriorCoordinate(next.getRow(), next.getColumn()) ||
                        (next.distanceTo(target) >= at.distanceTo(target) || Session.getRNG().nextDouble() < 0.33));
                at = next;
                ft = floor.tileAt(at.getRow(), at.getColumn());
                ft.setTerrain(new Terrain(streamTemplate));
                do {
                    next = Direction.random(false).shift(at);
                } while (!floor.isInteriorCoordinate(next.getRow(), next.getColumn()));
                //also add terrain to a random adjacent tile, to increase total size
                ft = floor.tileAt(next.getRow(), next.getColumn());
                ft.setTerrain(new Terrain(streamTemplate));
            } while (!at.equals(target));
        }
    }

}
