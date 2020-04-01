package main.progression.estate;

import java.io.Serializable;

public class NodeIndex implements Serializable {
    final int ROOM_INDEX;
    final int UPGRADE_INDEX;

    NodeIndex(int rmIdx, int upIdx) {
        ROOM_INDEX = rmIdx;
        UPGRADE_INDEX = upIdx;
    }
}
