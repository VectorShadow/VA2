package world.dungeon;

import main.Session;
import world.dungeon.theme.DungeonTheme;

import java.io.Serializable;

/**
 * Contains information required to build a specific dungeon.
 */
public class DungeonTemplate implements Serializable {
    private final int MINIMUM_FLOORS;
    private final int FLOOR_VARIANCE;
    public final DungeonTheme DUNGEON_THEME;

    public DungeonTemplate(int minFloors, int floorVar, DungeonTheme dungeonTheme) {
        MINIMUM_FLOORS = minFloors;
        FLOOR_VARIANCE = floorVar;
        DUNGEON_THEME = dungeonTheme;
    }

    int rollDepth() {
        return Session.getRNG().nextInt(FLOOR_VARIANCE) + MINIMUM_FLOORS;
    }
}
