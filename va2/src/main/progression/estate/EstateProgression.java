package main.progression.estate;

import world.item.define.LegacyResourceDefinitions;

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

    public static final int LIBRARY_STUDY_DESK = 0;
    //todo - more library nodes
    //todo - upgrade nodes for other rooms

    private ArrayList<EstateProgressionRoom> estateRooms = new ArrayList<>();

    public EstateProgression() {
        EstateProgressionRoom epr = new EstateProgressionRoom();
        ArrayList<ResourceRequirement> rrl = new ArrayList<>();
        ArrayList<NodeIndex> nil = new ArrayList<>();
        rrl.add(new ResourceRequirement(8, LegacyResourceDefinitions.LUMBER()));
        rrl.add(new ResourceRequirement(2, LegacyResourceDefinitions.CLOTH()));
        rrl.add(new ResourceRequirement(1, LegacyResourceDefinitions.SIMPLE_TOOLS()));
        epr.add(new EstateProgressionNode("Study Desk", rrl, nil));
        //todo - more library nodes
        estateRooms.add(epr);
        //todo - more rooms
    }

    public EstateProgressionRoom get(int roomIndex) {
        return estateRooms.get(roomIndex);
    }

    public EstateProgressionNode get(String nodeMenuName) {
        for (EstateProgressionRoom epr : estateRooms) {
            for (EstateProgressionNode epn : epr) {
                if (epn.toString().equals(nodeMenuName)) return epn;
            }
        }
        throw new IllegalArgumentException("Node named " + nodeMenuName + " did not exist.");
    }

    public EstateProgressionNode nodeAt(int roomIndex, int nodeIndex) {
        return nodeAt(new NodeIndex(roomIndex, nodeIndex));
    }

    EstateProgressionNode nodeAt(NodeIndex nodeIndex) {
        return estateRooms.get(nodeIndex.ROOM_INDEX).get(nodeIndex.UPGRADE_INDEX);
    }

    @Override
    public String toString() {
        String s = "Estate Progression:\n";
        for (EstateProgressionRoom epr : estateRooms) {
            for (EstateProgressionNode epn : epr) {
                s += "\n" + (epn.isUnlocked() ? "[Unlocked] " + epn.getName() : "[Locked] " + epn);
            }
        }
        return s;
    }
}
