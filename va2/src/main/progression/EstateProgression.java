package main.progression;

import java.io.Serializable;
import java.util.ArrayList;

public class EstateProgression implements Serializable {

    public static final int INDEX_LIBRARY = 0;
    public static final int INDEX_HALL_OF_ARMS = 1;
    public static final int INDEX_ARCHERY_RANGE = 2;
    public static final int INDEX_LABORATORY = 3;
    public static final int INDEX_TROPHY_HALL = 4;
    public static final int INDEX_MAUSOLEUM = 5;
    public static final int INDEX_FORGE = 6;
    public static final int INDEX_WORKSHOP = 7;
    public static final int INDEX_WAREHOUSE = 8;
    public static final int INDEX_ARMORY = 9;
    public static final int INDEX_RITUAL_CHAMBER = 10;

    private static final int ROOM_COUNT = 11;

    private ArrayList<ArrayList<EstateProgressionNode>> estateRooms = new ArrayList<>();

    public EstateProgression() {
        //todo - setup the base (locked) EstateProgression here
    }

    EstateProgressionNode nodeAt(NodeIndex nodeIndex) {
        return estateRooms.get(nodeIndex.ROOM_INDEX).get(nodeIndex.UPGRADE_INDEX);
    }

}
